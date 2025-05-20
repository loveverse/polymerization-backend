package com.loveverse.security.component;

import org.springframework.context.annotation.Bean;

/**
 * @author love
 * @since 2025/5/19 17:33
 */
public class ResourceServerAutoConfiguration {
    @Bean("pms")
    public PermissionService permissionService() {
        return new PermissionService();
    }
}
