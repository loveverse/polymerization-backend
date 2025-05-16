package com.loveverse.redis.util;

import com.loveverse.redis.exception.RedisOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


import javax.annotation.Resource;

/**
 * @author love
 * @since 2025/4/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DistributedLockUtil {

    private final RedissonClient redissonClient;


    /**
     * 尝试获取锁并执行操作
     *
     * @param lockKey   锁key
     * @param waitTime  等待时间(秒)
     * @param leaseTime 持有时间(秒)
     * @param supplier  要执行的操作
     */
    public <T> T tryLockAndExecute(String lockKey, long waitTime, long leaseTime, Supplier<T> supplier) {
        return tryLockAndExecuteInternal(lockKey, waitTime, leaseTime, supplier, false);
    }

    /**
     * 尝试获取锁并执行操作(无返回值)
     */
    public void tryLockAndExecute(String lockKey, long waitTime, long leaseTime, Runnable runnable) {
        tryLockAndExecute(lockKey, waitTime, leaseTime, () -> {
            runnable.run();
            return null;
        });
    }

    /**
     * 公平锁方式执行
     */
    public <T> T tryFairLockAndExecute(String lockKey, long waitTime, long leaseTime, Supplier<T> supplier) {
        return tryLockAndExecuteInternal(lockKey, waitTime, leaseTime, supplier, true);
    }

    /**
     * 内部方法：尝试获取锁并执行操作
     */
    private <T> T tryLockAndExecuteInternal(String lockKey, long waitTime, long leaseTime, Supplier<T> supplier, boolean isFair) {
        RLock lock = isFair ? redissonClient.getFairLock(lockKey) : redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS)) {
                try {
                    return supplier.get();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
            throw new RedisOperationException("获取分布式锁超时");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            handleException("获取分布式锁被中断", e);
            return null;
        } catch (Exception e) {
            handleException("获取分布式锁失败", e);
            return null;
        }
    }

    // ============================== 公共方法 ==============================

    private void handleException(String message, Exception e) {
        if (e instanceof InterruptedException) {
            log.error("Redis锁操作中断：{}", e.getMessage(), e);
            throw new RedisOperationException("获取分布式锁被中断", e);
        } else {
            log.error("{}：{}", message, e.getMessage(), e);
            throw new RedisOperationException(message, e);
        }
    }
}
