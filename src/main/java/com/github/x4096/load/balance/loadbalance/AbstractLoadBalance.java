package com.github.x4096.load.balance.loadbalance;

import com.github.x4096.load.balance.Invocation;
import com.github.x4096.load.balance.Server;

import java.util.List;

/**
 * @author 0x4096.peng@gmail.com
 * @project consistent-hash-algorithm
 * @datetime 2020/6/27 23:07
 * @description
 * @readme
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public Server select(List<Server> serverList, Invocation invocation) {
        if (null == serverList || serverList.size() == 0) {
            return null;
        }

        if (serverList.size() == 1) {
            return serverList.get(0);
        }

        return doSelect(serverList, invocation);
    }

    protected abstract Server doSelect(List<Server> serverList, Invocation invocation);

}
