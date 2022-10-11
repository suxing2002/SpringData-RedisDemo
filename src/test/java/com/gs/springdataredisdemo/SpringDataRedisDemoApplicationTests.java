package com.gs.springdataredisdemo;

import com.alibaba.fastjson2.JSON;
import com.gs.springdataredisdemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@SpringBootTest
class SpringDataRedisDemoApplicationTests {
    @Autowired
    @Qualifier(value = "CustomerRedisTemplate")
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @DisplayName("测试SpringData-Redis")
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name", "Red Had");
        Object name = redisTemplate.opsForValue().get("name");
        log.info("name : {}", name);
    }

    @DisplayName("测试存储实例")
    @Test
    void testSaveEntity() {
        redisTemplate.opsForValue().set("User:1", new User("小明" ,
                "123456" , 17 , "man"));
    }
    @DisplayName("测试StringRedisTemplate")
    @Test
    void testStringRedisTemplate(){
        String userJson = JSON.toJSONString(new User("小红", "123456", 16, "woman"));
        stringRedisTemplate.opsForValue().set("User:2", userJson);
        Object result =  JSON.parse(stringRedisTemplate.opsForValue().get("User:2"));
        log.info("user: {}", result);
    }
    @DisplayName("测试Hash")
    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("User:3", "name", "小蓝");
        stringRedisTemplate.opsForHash().put("User:3", "sex", "woman");
        stringRedisTemplate.opsForHash().put("User:3", "password", "123456");
        stringRedisTemplate.opsForHash().put("User:3", "age", "15");
        List<Object> objects = stringRedisTemplate.opsForHash().multiGet("User:3", Arrays.asList("name", "sex", "password", "age"));
        objects.forEach(System.out::println);
    }
}
