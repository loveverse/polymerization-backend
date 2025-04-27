package com.loveverse.wallpaper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author love
 * @since 2025/4/8
 */
@Data
@Component
@ConfigurationProperties(prefix = "user")
public class UserConfig {
    private String name;

    private String password;
}
