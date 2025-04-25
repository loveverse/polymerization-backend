package com.loveverse.redis;

import com.loveverse.redis.util.RedisUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author love
 * @since 2025/4/25
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@Import({RedisUtils.class, TestRedisConfiguration.TestConfig.class})
@SpringBootTest
public class TestRedisConfiguration {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedissonClient redisClient;

    @Test
    public void redisSetValueTest() {
        redisUtils.set("a", "111", 100);
        // 验证设置是否成功
        String value = redisUtils.get("a");
        System.out.println("Value from Redis: " + value);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public RedissonClient redissonClient() {
            Config config = new Config();
            SingleServerConfig singleServerConfig = config.useSingleServer()
                    .setAddress("redis://192.168.37.10:6379")
                    .setDatabase(0);
            return Redisson.create(config);
        }
    }
}
