package com.hopoong.sentinel_ops.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String slaveHost;

    @Value("${spring.data.redis.port}")
    private int slavePort;

    @Bean // 마스터 연결 설정 (쓰기 전용) - 기본 redisTemplate으로 설정
    public RedisTemplate<String, Object> redisTemplate() {
        return masterRedisTemplate();
    }

    // 마스터 연결 설정 (쓰기 전용)
    @Bean(name = "masterRedisTemplate")
    public RedisTemplate<String, Object> masterRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(masterConnectionFactory());


        // Key를 String으로 직렬화
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Object를 JSON으로 직렬화
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    // 슬레이브 연결 설정 (읽기 전용)
    @Bean(name = "slaveRedisTemplate")
    public RedisTemplate<String, Object> slaveRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(slaveConnectionFactory());


        // Key를 String으로 직렬화
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Object를 JSON으로 직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        // 직렬화에서 ObjectMapper를 미리 설정
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    /*
     * Redis 클라이언트(Lettuce/Jedis)가 마스터 정보를 얻는 방식:
     * Redis 클라이언트는 Sentinel에 접속하여 "mymaster"라는 마스터의 현재 위치(IP 및 포트)를 요청합니다.
     * Sentinel이 클라이언트에게 현재 마스터 Redis 서버의 정보를 제공합니다.
     * 장애가 발생해 마스터가 변경되면, Sentinel이 새로운 마스터 정보를 클라이언트에게 자동으로 갱신하여 전송합니다.
     */

    // 마스터에 연결될 ConnectionFactory (Sentinel 사용)
    @Bean
    public RedisConnectionFactory masterConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster") // Sentinel에서 관리하는 마스터 이름
                .sentinel("127.0.0.1", 26379) // Sentinel 서버 정보
                .sentinel("127.0.0.2", 26379); // 추가 Sentinel 서버
        return new LettuceConnectionFactory(sentinelConfig);
    }

    // 슬레이브에 연결될 ConnectionFactory (직접 슬레이브에 연결)
    @Bean
    public RedisConnectionFactory slaveConnectionFactory() {
        RedisStandaloneConfiguration slaveConfig = new RedisStandaloneConfiguration(slaveHost, slavePort);
        return new LettuceConnectionFactory(slaveConfig);
    }


}
