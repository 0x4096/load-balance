package com.github.x4096.load.balance.loadbalance;

import com.github.x4096.load.balance.Invocation;
import com.github.x4096.load.balance.Server;
import com.github.x4096.load.balance.loadbalance.hash.HashStrategy;

import java.util.List;

/**
 * hash 负载
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/16
 */
public class HashStrategyLoadBalance extends AbstractLoadBalance {

    private final HashStrategy hashStrategy;

    public HashStrategyLoadBalance(HashStrategy hashStrategy) {
        this.hashStrategy = hashStrategy;
    }

    @Override
    protected Server doSelect(List<Server> serverList, Invocation invocation) {
        return serverList.get(hashStrategy.hashCode(invocation.getHashKey()) % serverList.size());
    }

}
