package com.hopoong.redis_queue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisPoolConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;


    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(host, port); // 호스트와 포트를 필요에 맞게 설정하세요
    }
}
