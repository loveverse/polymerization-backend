package com.loveverse.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author love
 * @since 2025/4/19
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.gitee.sunchenbin.mybatis.actable.dao.*")
// 扫描Actable包，不手动指定包会找不到
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.loveverse.auth.*"})
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}