package com.loveverse.fast.common.config;

import com.loveverse.fast.common.global.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author love
 * @since 2025/4/9
 */
@Configuration
public class GlobalExceptionConfig {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler(Environment environment) {
        return new GlobalExceptionHandler(environment);
    }
}
