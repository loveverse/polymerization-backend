package com.loveverse.fast.common.registrar;

import cn.hutool.core.collection.CollectionUtil;
import com.loveverse.fast.common.annotation.EnableMyBatisPlus;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author love
 * @since 2025/4/9
 */
public class MapperScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableMyBatisPlus.class.getName()));
        if (mapperScanAttrs != null) {
            registerMapperScanner(registry, mapperScanAttrs);
        }
    }

    private void registerMapperScanner(BeanDefinitionRegistry registry, AnnotationAttributes annoAttrs) {
        String[] basePackages = annoAttrs.getStringArray("value");
        if (ObjectUtils.isEmpty(basePackages)) {
            // 如果没有指定 basePackages，则跳过注册 MapperScannerConfigurer
            return;
        }

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        builder.addPropertyValue("basePackage", String.join(",", basePackages));

        registry.registerBeanDefinition("mapperScannerConfigurer", builder.getBeanDefinition());
    }
}
