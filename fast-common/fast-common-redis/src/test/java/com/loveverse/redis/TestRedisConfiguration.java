package com.loveverse.redis;

import com.loveverse.redis.config.RedisProperties;
import com.loveverse.redis.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * @author love
 * @since 2025/4/25
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@Import({RedisUtils.class})
@SpringBootTest
public class TestRedisConfiguration {
    @Resource
    private RedisUtils redisUtils;

    @Test
    public void redisSetValueTest() {
        redisUtils.set("a", "111", 100);
    }
}
