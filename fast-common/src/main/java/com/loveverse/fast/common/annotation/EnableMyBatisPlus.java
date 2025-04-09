package com.loveverse.fast.common.annotation;

import com.loveverse.fast.common.config.MyBatisPlusConfig;
import com.loveverse.fast.common.registrar.MapperScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author love
 * @since 2025/4/9
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyBatisPlusConfig.class, MapperScannerRegistrar.class})
public @interface EnableMyBatisPlus {
    String[] value() default {};
}
