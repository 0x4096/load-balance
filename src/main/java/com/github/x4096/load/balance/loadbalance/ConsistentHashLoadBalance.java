package com.github.x4096.load.balance.loadbalance;

import com.github.x4096.load.balance.Invocation;
import com.github.x4096.load.balance.Server;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * @author 0x4096.peng@gmail.com
 * @project consistent-hash-algorithm
 * @datetime 2020/6/27 23:28
 * @description 一致性hash
 * @readme 参考 org.apache.dubbo.rpc.cluster.loadbalance.ConsistentHashLoadBalance
 * link http://blog.codinglabs.org/articles/consistent-hashing.html
 */
public class ConsistentHashLoadBalance extends AbstractLoadBalance {

    private final ConcurrentMap<String, ConsistentHashSelector> selectors = new ConcurrentHashMap<>();

    @Override
    protected Server doSelect(List<Server> serverList, Invocation invocation) {
        String key = invocation.getHashKey();
        int identityHashCode = System.identityHashCode(invocation);

        ConsistentHashSelector selector = selectors.get(key);
        if (null == selector || selector.identityHashCode != identityHashCode) {
            selectors.put(key, new ConsistentHashSelector(serverList, key, identityHashCode));
            selector = selectors.get(key);
        }
        return selector.select(invocation);
    }


    private static final class ConsistentHashSelector {

        /**
         * 虚拟服务
         */
        private final TreeMap<Long, Server> virtualServers;

        /**
         * 副本数
         */
        private final int replicaNumber;

        /**
         * 一致hashCode
         */
        private final int identityHashCode;

        /**
         *
         */
        private final int[] argumentIndex;

        Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

        public ConsistentHashSelector(List<Server> serverList, String key, int identityHashCode) {
            this.virtualServers = new TreeMap<>();
            this.identityHashCode = identityHashCode;
            this.replicaNumber = 160;
            String[] index = COMMA_SPLIT_PATTERN.split("0");
            this.argumentIndex = new int[index.length];
            for (int i = 0; i < index.length; i++) {
                argumentIndex[i] = Integer.parseInt(index[i]);
            }

            for (Server server : serverList) {
                for (int i = 0; i < replicaNumber / 4; i++) {
                    byte[] digest = md5(server.getIp() + i);
                    for (int h = 0; h < 4; h++) {
                        long m = hash(digest, h);
                        virtualServers.put(m, server);
                    }
                }
            }
        }


        public Server select(Invocation invocation) {
            String key = toKey(new Object[]{invocation.getHashKey()});
            byte[] digest = md5(key);
            return selectForKey(hash(digest, 0));
        }

        private String toKey(Object[] args) {
            StringBuilder buf = new StringBuilder();
            for (int i : argumentIndex) {
                if (i >= 0 && i < args.length) {
                    buf.append(args[i]);
                }
            }
            return buf.toString();
        }

        private Server selectForKey(long hash) {
            Map.Entry<Long, Server> entry = virtualServers.ceilingEntry(hash);
            if (null == entry) {
                entry = virtualServers.firstEntry();
            }
            return entry.getValue();
        }

        private long hash(byte[] digest, int number) {
            return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                    | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                    | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                    | (digest[number * 4] & 0xFF))
                    & 0xFFFFFFFFL;
        }

        private byte[] md5(String value) {
            MessageDigest md5;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
            md5.reset();
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            md5.update(bytes);
            return md5.digest();
        }
    }

}
