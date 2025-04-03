package com.loveverse.wallpaper.annotation;

import java.lang.annotation.*;

/**
 * @author love
 * @since 2025/4/3
 * @description 生成Swagger API文档字段枚举描述注解
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiEnumDescription {
    Class<? extends Enum<?>> value();
    String desc() default "label";
}
