package com.loveverse.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.loveverse.mybatis.handler.IdGenerator;
import com.loveverse.mybatis.handler.TimeObjectHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author love
 * @since 2025/4/18
 * @description
 * Spring 不会对 @Configuration 类进行 CGLIB 代理，而是直接调用原始方法。
 * 每次调用 @Bean 方法都会执行方法体（可能创建新实例，除非手动控制单例）。
 * proxyBeanMethods = false 可以提升启动速度和减少内存开销。
 */
@Slf4j
@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
public class MybatisAutoConfiguration  {

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