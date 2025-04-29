package com.loveverse.wallpaper;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.gitee.sunchenbin.mybatis.actable.dao.*")
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.loveverse.wallpaper.*"})
// 扫描Actable包，不手动指定包会找不到
//@SuppressWarnings("SpellCheckingInspection")
public class WallpaperWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WallpaperWebServiceApplication.class, args);
    }
}
