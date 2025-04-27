package com.loveverse.file.upload;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@MapperScan(value = {"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.loveverse.file.upload.mapper"})
// 扫描Actable包，不手动指定包会找不到
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.loveverse.file.upload.*"})
@SpringBootConfiguration
@EnableAutoConfiguration
public class FileUploadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploadServiceApplication.class, args);
    }
}
