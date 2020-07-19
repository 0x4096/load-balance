package com.github.x4096.load.balance.loadbalance.hash;

/**
 * hash 策略 相关hash策略参考 https://github.com/lexburner/consistent-hash-algorithm/blob/master/src/main/java/moe/cnkirito/consistenthash/HashStrategy.java
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/15
 */
public interface HashStrategy {

    /**
     * 根据服务源获取 hashCode
     *
     * @param origin 服务源，比如请求IP
     * @return hashCode
     */
    int hashCode(String origin);

}
