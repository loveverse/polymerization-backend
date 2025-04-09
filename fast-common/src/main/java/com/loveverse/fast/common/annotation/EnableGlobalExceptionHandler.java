package com.loveverse.fast.common.annotation;

import com.loveverse.fast.common.config.GlobalExceptionConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author love
 * @since 2025/4/9
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GlobalExceptionConfig.class) // 注意：导入配置类
public @interface EnableGlobalExceptionHandler {
}
