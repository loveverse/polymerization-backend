package com.loveverse.auth;

import com.loveverse.security.annotation.EnableResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author love
 * @since 2025/4/19
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
@EnableWebSecurity
//@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.loveverse.auth.mapper"})
// 扫描Actable包，不手动指定包会找不到
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.loveverse.auth.*"})
public class AuthServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AuthServiceApplication.class, args);
        run.getBean(DefaultSecurityFilterChain.class);
    }

}