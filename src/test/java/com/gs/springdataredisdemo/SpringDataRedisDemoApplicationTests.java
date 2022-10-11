package com.gs.springdataredisdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class SpringDataRedisDemoApplicationTests {
    @Autowired
    @Qualifier(value = "CustomerRedisTemplate")
    private RedisTemplate redisTemplate;
    @DisplayName("测试SpringData-Redis")
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name", "Red Had");
        Object name = redisTemplate.opsForValue().get("name");
        log.info("name : {}", name);
    }

}
