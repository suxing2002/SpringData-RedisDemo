package com.gs.springdataredisdemo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author GuoShuo
 * @Time 2022/10/11 16:46
 * @Version 1.0
 * @Description
 */
@SpringBootConfiguration
public class ApplicationConfig {
    /**
     * 原生的RedisTemplate使用的序列化组件为java原生序列化器,存储到redis后可读性差,存储量大,存储效率低
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String , Object> CustomerRedisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String , Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
        return redisTemplate;
    }
}
