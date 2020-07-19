package com.github.x4096.load.balance;

import com.github.x4096.load.balance.loadbalance.ConsistentHashLoadBalance;
import org.junit.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/16
 */
public class ConsistentHashLoadBalanceTest extends BaseLoadBalanceTest {

    @Test
    public void test() {
        ConsistentHashLoadBalance consistentHashLoadBalance = new ConsistentHashLoadBalance();

        Server server;
        server = consistentHashLoadBalance.doSelect(serverList, new Invocation("192.168.1.1"));
        System.err.println(server);

        serverList.add(new Server(10, "192.168.0.123"));
        serverList.add(new Server(10, "192.168.0.120"));
        serverList.add(new Server(10, "10.168.0.122"));
        serverList.add(new Server(10, "11.168.0.121"));

        server = consistentHashLoadBalance.select(serverList, new Invocation("192.168.1.1"));
        System.err.println(server);
    }

}
