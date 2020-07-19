package com.github.x4096.load.balance;

import com.github.x4096.load.balance.loadbalance.HashStrategyLoadBalance;
import com.github.x4096.load.balance.loadbalance.hash.CRCHashStrategy;
import com.github.x4096.load.balance.loadbalance.hash.HashStrategy;
import com.github.x4096.load.balance.loadbalance.hash.JdkHashStrategy;
import org.junit.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/16
 */
public class HashStrategyLoadBalanceTest extends BaseLoadBalanceTest {

    @Test
    public void hash() {
        HashStrategy hashStrategy = new JdkHashStrategy();
        HashStrategyLoadBalance hashStrategyLoadBalance = new HashStrategyLoadBalance(hashStrategy);

        Server server;
        server = hashStrategyLoadBalance.select(serverList, new Invocation("192.168.1.1"));
        System.err.println(server);

        hashStrategy = new CRCHashStrategy();
        hashStrategyLoadBalance = new HashStrategyLoadBalance(hashStrategy);
        server = hashStrategyLoadBalance.select(serverList, new Invocation("192.168.1.1"));
        System.err.println(server);
    }

}
