package com.github.x4096.load.balance.loadbalance.hash;

/**
 * FNV1_32_HASH 算法
 *
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/15
 */
public class FnvHashStrategy implements HashStrategy {

    private static final long FNV_32_INIT = 2166136261L;
    private static final int FNV_32_PRIME = 16777619;

    @Override
    public int hashCode(String origin) {
        int hash = (int) FNV_32_INIT;
        for (int i = 0; i < origin.length(); i++) {
            hash = (hash ^ origin.charAt(i)) * FNV_32_PRIME;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        hash = Math.abs(hash);
        return hash;
    }

}
