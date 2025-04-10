package com.loveverse.fast.common.annotation;

import com.loveverse.fast.common.config.GlobalExceptionConfig;
import com.loveverse.fast.common.config.JacksonConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author love
 * @since 2025/4/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JacksonConfig.class) // 注意：导入配置类
public @interface EnableJackson {
}
