package com.loveverse.oss.thirdparty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class OssThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssThirdPartyApplication.class, args);
    }
}
