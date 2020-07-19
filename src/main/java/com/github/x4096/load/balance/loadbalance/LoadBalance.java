package com.github.x4096.load.balance.loadbalance;

import com.github.x4096.load.balance.Invocation;
import com.github.x4096.load.balance.Server;

import java.util.List;

/**
 * @author 0x4096.peng@gmail.com
 * @project consistent-hash-algorithm
 * @datetime 2020/6/27 22:59
 * @description 负载均衡
 * @readme
 */
public interface LoadBalance {

    /**
     * select one server in list.
     *
     * @param serverList serverList
     * @param invocation invocation
     * @return selected server
     */
    Server select(List<Server> serverList, Invocation invocation);

}
