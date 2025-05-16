package com.loveverse.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loveverse.redis.config.RedisProperties;
import com.loveverse.redis.util.DistributedLockUtil;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author love
 * @since 2025/4/21
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RedisProperties.class)
@RequiredArgsConstructor
public class RedisAutoConfiguration {
    // 这里需要使用,所以直接注入使用
    private final RedisProperties redisProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty("spring.redis.cluster.nodes")
    public RedissonClient redissonClusterClient() {
        Config config = new Config();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        config.setCodec(new JsonJacksonCodec(objectMapper));
        ClusterServersConfig clusterServersConfig = config.useClusterServers()
                .setScanInterval(2000);
        if (redisProperties.getPassword() != null) {
            clusterServersConfig.setPassword(redisProperties.getPassword());
        }
        redisProperties.getCluster().getNodes().forEach(node -> {
            clusterServersConfig.addNodeAddress("redis://" + node);
        });

        log.info("✅redisson插件集群部署初始化成功...");
        return Redisson.create(config);
    }

    // 调用 Redisson 方法在销毁前调用
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "spring.redis.host")
    public RedissonClient redissonSingleClient() {
        Config config = new Config();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        config.setCodec(new JsonJacksonCodec(objectMapper));
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setDatabase(redisProperties.getDatabase());
        if (redisProperties.getPassword() != null) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }

        log.info("✅redisson插件单机部署初始化成功...");
        return Redisson.create(config);
    }

    // 这里不需要直接使用,所以使用@Bean注入
    @Bean
    @ConditionalOnMissingBean
    public RedisUtils redisUtils(RedissonClient redissonClient) {
        return new RedisUtils(redissonClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public DistributedLockUtil distributedLockUtil(RedissonClient redissonClient) {
        return new DistributedLockUtil(redissonClient);
    }

}
