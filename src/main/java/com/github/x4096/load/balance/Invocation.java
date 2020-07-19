package com.github.x4096.load.balance;

/**
 * @author 0x4096.peng@gmail.com
 * @project consistent-hash-algorithm
 * @datetime 2020/6/27 23:01
 * @description
 * @readme
 */
public class Invocation {

    private String hashKey;

    public Invocation() {
    }

    public Invocation(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "hashKey='" + hashKey + '\'' +
                '}';
    }

}
