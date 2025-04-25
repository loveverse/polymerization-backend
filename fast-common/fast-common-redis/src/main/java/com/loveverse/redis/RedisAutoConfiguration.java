package com.loveverse.redis;

import com.loveverse.redis.config.RedisProperties;
import com.loveverse.redis.util.DistributedLockUtil;
import com.loveverse.redis.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author love
 * @since 2025/4/21
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration {
    @Resource
    private  RedisProperties redisProperties;

    @Bean
    @ConditionalOnProperty("spring.redis.cluster.nodes")
    public RedissonClient redissonClusterClient() {
        Config config = new Config();
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

    @Bean
    @ConditionalOnProperty(name = "spring.redis.host")
    public RedissonClient redissonSingleClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setDatabase(redisProperties.getDatabase());
        if (redisProperties.getPassword() != null) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        log.info("✅redisson插件单机部署初始化成功...");
        return Redisson.create(config);
    }

    @Bean
    public RedisUtils redisUtils(){
        return new RedisUtils();
    }

    @Bean
    public DistributedLockUtil distributedLockUtil(){
        return new DistributedLockUtil();
    }
}
