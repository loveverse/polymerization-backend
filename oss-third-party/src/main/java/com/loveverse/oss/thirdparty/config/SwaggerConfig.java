package com.loveverse.oss.thirdparty.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi wallpaperApi() {
        return GroupedOpenApi.builder().group("文件上传接口文档").packagesToScan("com.loveverse.oss.thirdparty.controller")
                .pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("oss文件上传")
                        .contact(new Contact().name("loveverse").email("loveverse@gmail.com"))
                        .description("API文档")
                        .version("1.0")
                        .license(new License().name("Apache 2.0"))
                ).components(new Components());
    }
}
