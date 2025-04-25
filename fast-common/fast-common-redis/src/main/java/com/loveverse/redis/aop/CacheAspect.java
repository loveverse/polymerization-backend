package com.loveverse.redis.aop;

import com.loveverse.redis.annotation.CacheEvict;
import com.loveverse.redis.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @author love
 * @since 2025/4/24
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class CacheAspect {
    private final RedissonClient redissonClient;

    private final ExpressionParser parser;

    @Around("@annotation(cacheable)")
    public Object aroundCacheable(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        String key = buildCacheKey(joinPoint, cacheable.key());
        RBucket<Object> bucket = redissonClient.getBucket(key);

        // 先尝试从缓存获取
        Object cachedValue = bucket.get();
        if (cachedValue != null) {
            log.debug("缓存命中，key: {}", key);
            return cachedValue;
        }

        // 执行方法获取实际值
        Object result = joinPoint.proceed();

        // 存入缓存
        if (result != null) {
            long ttl = cacheable.timeUnit().toSeconds(cacheable.ttl());
            bucket.set(result, ttl, TimeUnit.SECONDS);
            log.debug("缓存设置成功，key: {}, ttl: {} 秒", key, ttl);
        }

        return result;
    }

    @Around("@annotation(cacheEvict)")
    public Object aroundCacheEvict(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        try {
            return joinPoint.proceed();
        } finally {
            String key = buildCacheKey(joinPoint, cacheEvict.key());
            redissonClient.getBucket(key).delete();
            log.debug("缓存删除成功，key: {}", key);
        }
    }

    private String buildCacheKey(ProceedingJoinPoint joinPoint, String keyExpression) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            context.setVariable("arg" + i, joinPoint.getArgs()[i]);
        }
        return parser.parseExpression(keyExpression).getValue(context, String.class);
    }
}
