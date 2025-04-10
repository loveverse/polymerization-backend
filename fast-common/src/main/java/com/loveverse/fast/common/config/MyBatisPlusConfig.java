package com.loveverse.fast.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.loveverse.fast.common.annotation.EnableMyBatisPlus;
import com.loveverse.fast.common.handler.IdGenerator;
import com.loveverse.fast.common.handler.TimeObjectHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @author love
 * @since 2025/4/9
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfig {

    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建mybatis plus拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        log.info("✅mybatis插件初始化成功...");
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public TimeObjectHandle timeObjectHandle() {
        return new TimeObjectHandle();
    }

    @Bean
    @ConditionalOnMissingBean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }
}
