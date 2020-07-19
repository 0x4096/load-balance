package com.github.x4096.load.balance.loadbalance;

import com.github.x4096.load.balance.Invocation;
import com.github.x4096.load.balance.Server;

import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * 加权随机
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/15
 */
public class WeightRandomLoadBalance extends AbstractLoadBalance {

    /*
        实现加权随机的方式有多种 假设有此类情况 {A:5，B:2，C:2，D:1}
     */

    /*
        方案一: 扩展这个集合，使每一项出现的次数与其权重正相关
        那么上述的例子扩展后是这样 {A,A,A,A,A,B,B,C,C,D} 然后在利用随机算法随机获取其中的值
        选取的时间复杂度是O(1)，但是空间复杂度上升，主要取决于每个节点的权重数量，权重越大，空间浪费越大
     */

    /*
        方案二: 计算权重之和为 sum， 在 1-sum 之间（左闭右闭）随机选择一个数为 r ，然后再统计遍历的项的权重之和，如果遇到的权重大于 r 则选择该项
        这样做的好处是没有空间浪费，但时间复杂度上升至O(n)
     */


    @Override
    protected Server doSelect(List<Server> serverList, Invocation invocation) {
        return methodTwo(serverList);
    }


    /**
     * 方案一
     */
    private Server methodOne(List<Server> serverList) {
        List<Server> capacityServerList = new ArrayList<>(serverList.size());
        serverList.forEach(server -> IntStream.rangeClosed(1, server.getWeight()).forEach(
                t -> capacityServerList.add(new Server(server.getWeight(), server.getIp()))));
        return capacityServerList.get(ThreadLocalRandom.current().nextInt(serverList.size()));
    }


    /**
     * 方式二
     */
    private Server methodTwo(List<Server> serverList) {
        int weightSum = serverList.stream().mapToInt(Server::getWeight).sum();
        weightSum += 1;
        int r = ThreadLocalRandom.current().nextInt(1, weightSum);
        int total = 0;
        for (Server server : serverList) {
            total += server.getWeight();
            if (total >= r) {
                return server;
            }
        }
        throw new IllegalSelectorException();
    }

}
