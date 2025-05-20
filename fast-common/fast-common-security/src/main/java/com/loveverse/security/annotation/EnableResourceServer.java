package com.loveverse.security.annotation;

import com.loveverse.security.component.ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author love
 * @since 2025/5/19 17:34
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ResourceServerAutoConfiguration.class})
public @interface EnableResourceServer {
}
