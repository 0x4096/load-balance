package com.github.x4096.load.balance.loadbalance.hash;

/**
 * JDK hash算法
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/15
 */
public class JdkHashStrategy implements HashStrategy {

    @Override
    public int hashCode(String origin) {
        return origin.hashCode();
    }

}
