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
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.loveverse.wallpaper.*"}) // 扫描Actable包，不手动指定包会找不到
//@SuppressWarnings("SpellCheckingInspection")
public class WallpaperModuleWebApplication {
    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(WallpaperModuleWebApplication.class, args).getEnvironment();
        String path = env.getProperty("server.servlet.context-path", "");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application: '{}' is running Success! \n\t" +
                        "Local URL: \thttp://localhost:{}\n\t" +
                        "Document:\thttp://localhost:{}/doc.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port") + path,
                env.getProperty("server.port") + path);
        //SpringApplication.run(WallpaperModuleWebApplication.class, args);
    }

}
