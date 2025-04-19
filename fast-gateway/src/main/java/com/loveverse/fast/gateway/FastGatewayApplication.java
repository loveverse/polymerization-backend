package com.loveverse.fast.gateway;

import com.alibaba.cloud.nacos.NacosConfigAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {NacosConfigAutoConfiguration.class}) // 排除配置中心
public class FastGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastGatewayApplication.class, args);
    }

}
