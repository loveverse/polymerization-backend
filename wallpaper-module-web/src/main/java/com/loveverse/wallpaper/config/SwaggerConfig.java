package com.loveverse.wallpaper.config;

import com.loveverse.wallpaper.annotation.ApiEnumDescription;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.ObjectSchema;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi wallpaperApi() {
        return GroupedOpenApi.builder().group("wallpaper").packagesToScan("com.loveverse.wallpaper.controller")
                .pathsToMatch("/**").addOpenApiCustomiser(openApiCustomizer()).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        log.info("加载swagger成功...");
        return new OpenAPI()
                .info(new Info().title("壁纸系统标题")
                        .contact(new Contact().name("loveverse").email("loveverse@gmail.com"))
                        .description("API文档")
                        .version("1.0")
                        .license(new License().name("Apache 2.0"))

                ).components(new Components());
    }

    /**
     * 配置一个操作自定义器，用于定制Swagger/OpenAPI中的操作（schema）对象
     * 该自定义器主要关注于处理API操作中的枚举类型参数描述
     *
     * @return OpenApiCustomiser 接口的实现，用于定制操作对象
     */
    @Bean
    public OpenApiCustomiser openApiCustomizer() {
        return openApi -> {
            openApi.getPaths().forEach((path, pathItem) -> {
                pathItem.readOperations().forEach((operation -> {
                    // 只处理 @RequestBody 的请求
                    if (operation.getRequestBody() != null) {
                        Schema<?> schema = operation.getRequestBody()
                                .getContent()
                                .get("application/json")
                                .getSchema();
                        String $ref = schema.get$ref();
                        if ($ref != null) {
                            Schema<?> objectSchema = openApi.getComponents().getSchemas().get($ref.substring($ref.lastIndexOf("/") + 1));
                            if (objectSchema.getProperties() != null) {
                                objectSchema.getProperties().forEach((fieldName, fieldSchema) -> {
                                    try {
                                        // todo 后期可能字段不在dto包中，需要优化
                                        String className = "com.loveverse.wallpaper.dto." + $ref.replace("#/components/schemas/", "");
                                        Class<?> dtoClass = Class.forName(className);
                                        Field field = dtoClass.getDeclaredField(fieldName);
                                        field.setAccessible(true);
                                        // 检查字段是否有 @ApiEnumDescription
                                        ApiEnumDescription annotation = field.getAnnotation(ApiEnumDescription.class);
                                        if (annotation != null) {
                                            // 提取枚举值和描述
                                            String enumDescription = extractEnumDescription(annotation.value(), annotation.desc());
                                            String description = fieldSchema.getDescription();
                                            log.info(111 + "{}{}", description, enumDescription);
                                            fieldSchema.setDescription(description + enumDescription);
                                            //fieldSchema.setEnum(getEnumValues(annotation.value())); // 设置可选值
                                        }
                                    } catch (Exception e) {
                                        log.warn("Failed to process @ApiEnumDescription for field: {}", fieldName, e);
                                    }
                                });
                            }
                        }
                    }
                }));
            });
        };
    }



    // 提取枚举描述（如 "1 - 最新; 2 - 热门"）
    private String extractEnumDescription(Class<? extends Enum<?>> enumClass, String descField) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
                Field valueField = enumClass.getDeclaredField("value");
                Field descFieldObj = enumClass.getDeclaredField(descField);
                valueField.setAccessible(true);
                descFieldObj.setAccessible(true);
                sb.append(valueField.get(enumConstant))
                        .append(":")
                        .append(descFieldObj.get(enumConstant))
                        .append(";");
            }
        } catch (Exception e) {
            log.error("Failed to extract enum description", e);
        }
        // 首尾添加[]
        sb.insert(0, "[").append("]");
        return sb.toString();
    }

    // 获取枚举所有值（如 ["LATEST", "HOT"]）
    private List<String> getEnumValues(Class<? extends Enum<?>> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
