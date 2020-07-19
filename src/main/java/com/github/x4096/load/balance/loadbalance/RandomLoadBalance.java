package com.github.x4096.load.balance.loadbalance;

import com.github.x4096.load.balance.Invocation;
import com.github.x4096.load.balance.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 0x4096.peng@gmail.com
 * @project consistent-hash-algorithm
 * @datetime 2020/6/27 23:10
 * @description 随机负载
 * @readme
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected Server doSelect(List<Server> serverList, Invocation invocation) {
        return serverList.get(ThreadLocalRandom.current().nextInt(serverList.size()));
    }

}
