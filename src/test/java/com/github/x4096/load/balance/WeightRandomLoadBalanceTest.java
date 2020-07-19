package com.github.x4096.load.balance;

import com.github.x4096.load.balance.loadbalance.WeightRandomLoadBalance;
import org.junit.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/15
 */
public class WeightRandomLoadBalanceTest extends BaseLoadBalanceTest {

    @Test
    public void test() {
        WeightRandomLoadBalance weightRandomLoadBalance = new WeightRandomLoadBalance();
        Server server;
        for (int i = 0; i < 15; i++) {
            server = weightRandomLoadBalance.select(serverList, new Invocation());
            System.err.println(server);
        }
    }

}
