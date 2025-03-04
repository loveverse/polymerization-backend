package com.loveverse.oss.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OssThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssThirdPartyApplication.class, args);
    }
}
