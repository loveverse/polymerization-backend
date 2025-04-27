package com.loveverse.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author love
 * @since 2025/4/27
 */
@Slf4j
@Configuration
public class StartupLoggingConfiguration implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableEnvironment env = event.getApplicationContext().getEnvironment();
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
