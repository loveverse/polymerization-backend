package com.loveverse.auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author love
 * @since 2025/5/16 14:36
 */
@Slf4j
@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder().group("用户认证接口文档").packagesToScan("com.loveverse.auth.controller")
                .pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .contact(new Contact().name("loveverse").email("loveverse@gmail.com"))
                        .description("API文档")
                        .version("1.0")
                        .license(new License().name("Apache 2.0"))
                ).components(new Components());
    }

}
