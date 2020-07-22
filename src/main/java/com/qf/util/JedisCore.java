package com.qf.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: brandshop
 * @description: 封装操作Redis方法
 * @author: money
 * @create: 2020-07-18 14:01
 */
public class JedisCore {
//    private String host;
//    private int port;
//    private String pass;
    //客户端对象
    private Jedis jedis;
    public JedisCore(String host,int port,String pass){
        //连接池
        JedisPool pool = new JedisPool(host,port);
        //获取客户端对象
        jedis = pool.getResource();
        jedis.auth(pass);
    }
    //新增
    public void set(String key,String val,int seconds){
        //setex设置值的同时，指定生存时间（每次向Redis中添加数据时，尽量都设置生存时间）
        jedis.setex(key,seconds,val);
    }

    //删除
    public void del(String key){
        jedis.del(key);
    }

    //查询
    public String get(String key){
        return jedis.get(key);
    }

    //系统
    public boolean checkKey(String key){
        //查看某一个key是否存在（1 - key存在，0-key不存在
        return jedis.exists(key);
    }

    public long ttl(String key){
        //pttl
        //查看key的剩余生存时间，单位为秒或毫秒（-2 - 当前key不存在，-1 - 当前key没有设置生存时间，具体剩余的生存时间）
        return jedis.ttl(key);
    }

    //查询指定前缀开头的key的数量 主要用于记录用户输错密码的次数
    public int keys(String key){
        return jedis.keys(key).size();
    }

    //设置key的有效期
    public void expire(String key,int seconds){
        //设置key的生存时间，单位可以为秒或毫秒，设置key还能活多久
        jedis.expire(key,seconds);
    }

}
