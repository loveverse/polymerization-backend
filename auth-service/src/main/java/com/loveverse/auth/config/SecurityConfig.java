package com.loveverse.auth.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;

import javax.servlet.Filter;

/**
 * @author love
 * @since 2025/4/23
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig() {
        super();
    }

    @Override
    public SecurityExpressionHandler<FilterInvocation> webSecurityExpressionHandler() {
        return super.webSecurityExpressionHandler();
    }

    @Override
    public Filter springSecurityFilterChain() throws Exception {
        return super.springSecurityFilterChain();
    }

    @Override
    public WebInvocationPrivilegeEvaluator privilegeEvaluator() {
        return super.privilegeEvaluator();
    }

    @Override
    public void setFilterChainProxySecurityConfigurer(ObjectPostProcessor<Object> objectPostProcessor, ConfigurableListableBeanFactory beanFactory) throws Exception {
        super.setFilterChainProxySecurityConfigurer(objectPostProcessor, beanFactory);
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        super.setImportMetadata(importMetadata);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        super.setBeanClassLoader(classLoader);
    }
}
