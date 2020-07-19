package com.github.x4096.load.balance;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/16
 */
public class BaseLoadBalanceTest {

    protected List<Server> serverList;

    @Before
    public void serverList() {
        List<Server> serverList = new ArrayList<>(ipArray.length);
        Server server;
        for (String s : ipArray) {
            server = new Server(ThreadLocalRandom.current().nextInt(1, 100), s);
            serverList.add(server);
        }
        this.serverList = serverList;
    }

    static final String[] ipArray = {
            "125.108.88.51",
            "103.79.155.42",
            "212.129.42.14",
            "125.108.83.109",
            "183.89.43.127",
            "125.110.95.237",
            "36.91.144.227",
            "18.179.204.190",
            "103.98.79.225",
            "103.245.198",
            "94.236.193.133",
            "163.172.93.183",
            "11.20.101.62",
            "200.69.77.10",
            "200.122.228.59",
            "118.24.89.206",
            "119.29.177.120",
            "119.82.252.25",
            "37.247.209.179",
            "191.103.219.131",
            "212.129.15.124",
            "95.31.5.29",
            "218.76.253.201",
            "138.0.89.136",
            "95.10.49.74",
            "212.83.139.79",
            "36.67.66.202",
            "80.82.68.8",
            "183.92.198.157",
            "212.83.152.2"
    };

}
