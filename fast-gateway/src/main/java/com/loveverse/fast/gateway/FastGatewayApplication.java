package com.loveverse.fast.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication      // 排除mysql配置
public class FastGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastGatewayApplication.class, args);
    }

}
