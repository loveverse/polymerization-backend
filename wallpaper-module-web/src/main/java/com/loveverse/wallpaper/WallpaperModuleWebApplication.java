package com.loveverse.wallpaper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.gitee.sunchenbin.mybatis.actable.dao.*")
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*"})
//@SuppressWarnings("SpellCheckingInspection")
public class WallpaperModuleWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WallpaperModuleWebApplication.class, args);

    }

}
