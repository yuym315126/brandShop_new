package com.qf.config;

import com.qf.util.JedisCore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: brandshop
 * @description: Jedis的配置
 * @author: money
 * @create: 2020-07-18 14:25
 */
@Configuration
public class JedisConfig {
    @Value("${brandshop.redis.host}")
    private String host;
    @Value("${brandshop.redis.port}")
    private int port;
    @Value("${brandshop.redis.pass}")
    private String pass;

    @Bean
    public JedisCore createJC(){
        return new JedisCore(host,port,pass);
    }
}
