package com.github.x4096.load.balance;

/**
 * @author 0x4096.peng@gmail.com
 * @project consistent-hash-algorithm
 * @datetime 2020/6/27 23:02
 * @description
 * @readme
 */
public class Server {

    /**
     * 权重 范围 1-100
     */
    private int weight = 100;

    /**
     * 服务IP
     */
    private String ip;

    public Server() {
    }

    public Server(int weight, String ip) {
        this.weight = weight;
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Server{" +
                "weight=" + weight +
                ", ip='" + ip + '\'' +
                '}';
    }

}
