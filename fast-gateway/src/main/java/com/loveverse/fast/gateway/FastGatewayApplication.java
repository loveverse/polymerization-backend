package com.loveverse.fast.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FastGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastGatewayApplication.class, args);
    }

}
