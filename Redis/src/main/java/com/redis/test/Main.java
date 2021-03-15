package com.redis.test;

import RedisUtil.RedisClient;
import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args){
        Jedis redis = RedisClient.getClient("conf.yml");
        redis.set("hello","word");
        String value = redis.get("hello");
        System.out.println(value);
    }
}
