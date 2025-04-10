package com.loveverse.oss.thirdparty;

import com.loveverse.fast.common.annotation.EnableGlobalExceptionHandler;
import com.loveverse.fast.common.annotation.EnableJackson;
import com.loveverse.fast.common.annotation.EnableMyBatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@MapperScan(value = {"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.loveverse.oss.thirdparty.mapper"})
// 扫描Actable包，不手动指定包会找不到
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.loveverse.oss.thirdparty.*"})
@EnableMyBatisPlus
@EnableJackson
@EnableGlobalExceptionHandler
@SpringBootApplication
public class OssThirdPartyApplication {
    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(OssThirdPartyApplication.class, args).getEnvironment();
        String path = env.getProperty("server.servlet.context-path", "");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application: '{}' is running Success! \n\t" +
                        "Local URL: \thttp://localhost:{}\n\t" +
                        "Document:\thttp://localhost:{}/doc.html\n\t" +
                        "Swagger:\thttp://localhost:{}/swagger-ui/index.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port") + path,
                env.getProperty("server.port") + path,
                env.getProperty("server.port") + path);
    }
}
