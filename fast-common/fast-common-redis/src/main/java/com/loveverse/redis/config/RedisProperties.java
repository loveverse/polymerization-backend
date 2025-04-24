package com.loveverse.redis.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author love
 * @since 2025/4/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host;
    private int port;
    private String password;
    private int database;
    private Cluster cluster;


    @Getter
    @Setter
    public static class Cluster {
        private List<String> nodes;
    }
}
