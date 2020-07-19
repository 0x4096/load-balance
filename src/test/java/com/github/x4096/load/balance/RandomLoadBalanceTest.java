package com.github.x4096.load.balance;

import com.github.x4096.load.balance.loadbalance.RandomLoadBalance;
import org.junit.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/19
 */
public class RandomLoadBalanceTest extends BaseLoadBalanceTest {

    @Test
    public void test() {
        RandomLoadBalance randomLoadBalance = new RandomLoadBalance();
        Server server;
        for (int i = 0; i < 5; i++) {
            server = randomLoadBalance.select(serverList, new Invocation());
            System.err.println(server);
        }
    }

}
