package com.loveverse.redis.util;

import com.loveverse.redis.exception.RedisOperationException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisConnectionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author love
 * @since 2025/4/24
 */
@Slf4j
@Component

public class RedisUtils {
    @Resource
    private  RedissonClient redissonClient;


    // ============================== Common ==============================

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public <T> void set(String key, T value, long time) {
        validateKey(key);
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            if (time > 0) {
                bucket.set(value, time, TimeUnit.SECONDS);
            } else {
                bucket.set(value);
            }
        } catch (Exception e) {
            handleException("Redis set操作失败", e);
        }
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public <T> T get(String key) {
        validateKey(key);
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            return bucket.get();
        } catch (Exception e) {
            handleException("Redis get操作失败", e);
            return null; // 返回null以避免异常传播
        }
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个或多个key
     */
    public void delete(String... keys) {
        if (keys == null || keys.length == 0) {
            log.warn("删除缓存失败：keys为空或null");
            return;
        }
        try {
            if (keys.length == 1) {
                redissonClient.getBucket(keys[0]).delete();
            } else {
                redissonClient.getKeys().delete(keys);
            }
        } catch (Exception e) {
            handleException("Redis delete操作失败", e);
        }
    }

    // ============================== Lock ==============================

    /**
     * 获取分布式锁
     *
     * @param lockKey   锁key
     * @param waitTime  等待时间(秒)
     * @param leaseTime 持有时间(秒)
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        validateKey(lockKey);
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            handleException("获取分布式锁被中断", e);
            return false;
        } catch (Exception e) {
            handleException("获取分布式锁失败", e);
            return false;
        }
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey 锁key
     */
    public void unlock(String lockKey) {
        validateKey(lockKey);
        try {
            RLock lock = redissonClient.getLock(lockKey);
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Exception e) {
            handleException("释放分布式锁失败", e);
        }
    }

    // ============================== Map ==============================

    public <K, V> void hset(String key, K field, V value) {
        validateKey(key);
        try {
            RMap<K, V> map = redissonClient.getMap(key);
            map.put(field, value);
        } catch (Exception e) {
            handleException("Redis hset操作失败", e);
        }
    }

    public <K, V> V hget(String key, K field) {
        validateKey(key);
        try {
            RMap<K, V> map = redissonClient.getMap(key);
            return map.get(field);
        } catch (Exception e) {
            handleException("Redis hget操作失败", e);
            return null; // 返回null以避免异常传播
        }
    }

    // ============================== List ==============================

    public <T> void lpush(String key, T value) {
        validateKey(key);
        try {
            RDeque<T> deque = redissonClient.getDeque(key);
            deque.push(value);
        } catch (Exception e) {
            handleException("Redis lpush操作失败", e);
        }
    }

    public <T> T rpop(String key) {
        validateKey(key);
        try {
            RDeque<T> deque = redissonClient.getDeque(key);
            return deque.pollLast();
        } catch (Exception e) {
            handleException("Redis rpop操作失败", e);
            return null; // 返回null以避免异常传播
        }
    }

    // ============================== Set ==============================

    public <T> void sadd(String key, T... members) {
        validateKey(key);
        try {
            RSet<T> set = redissonClient.getSet(key);
            set.addAll(Arrays.asList(members));
        } catch (Exception e) {
            handleException("Redis sadd操作失败", e);
        }
    }

    public <T> Set<T> smembers(String key) {
        validateKey(key);
        try {
            RSet<T> set = redissonClient.getSet(key);
            return set.readAll();
        } catch (Exception e) {
            handleException("Redis smembers操作失败", e);
            return null; // 返回null以避免异常传播
        }
    }

    // ============================== 高级功能 ==============================

    /**
     * 批量获取缓存
     */
    public <T> Map<String, T> batchGet(Collection<String> keys) {
        if (keys == null || keys.isEmpty()) {
            log.warn("批量获取缓存失败：keys为空或null");
            return null; // 返回null以避免异常传播
        }
        try {
            RBuckets buckets = redissonClient.getBuckets();
            return buckets.get(keys.toArray(new String[0]));
        } catch (Exception e) {
            handleException("Redis批量get操作失败", e);
            return null; // 返回null以避免异常传播
        }
    }

    /**
     * 批量设置缓存
     */
    public <T> void batchSet(Map<String, T> keyValueMap, long time) {
        if (keyValueMap == null || keyValueMap.isEmpty()) {
            log.warn("批量设置缓存失败：keyValueMap为空或null");
            return;
        }
        if (time < 0) {
            throw new IllegalArgumentException("时间参数不能为负数");
        }
        try {
            RBuckets buckets = redissonClient.getBuckets();
            buckets.set(keyValueMap);
            if (time > 0) {
                for (String key : keyValueMap.keySet()) {
                    RBucket<T> bucket = redissonClient.getBucket(key);
                    bucket.expire(Duration.ofSeconds(time));
                }
            }
        } catch (Exception e) {
            handleException("Redis批量set操作失败", e);
        }
    }

    /**
     * 执行原子操作
     */
    public <T> T executeInTransaction(TransactionOptions options, TransactionalTask<T> task) {
        RTransaction transaction = redissonClient.createTransaction(options);
        try {
            T result = task.execute(transaction);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            handleException("Redis事务执行失败", e);
            return null; // 返回null以避免异常传播
        }
    }

    @FunctionalInterface
    public interface TransactionalTask<T> {
        T execute(RTransaction transaction) throws Exception;
    }

    // ============================== 公共方法 ==============================

    private void validateKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("键不能为空或null");
        }
    }

    private void handleException(String message, Exception e) {
        if (e instanceof RedisConnectionException) {
            log.error("Redis连接异常：{}", e.getMessage(), e);
            throw new RedisOperationException("Redis连接失败，请检查网络或Redis服务状态", e);
        } else if (e instanceof TimeoutException) {
            log.error("Redis操作超时：{}", e.getMessage(), e);
            throw new RedisOperationException("Redis操作超时，请检查网络延迟或Redis负载", e);
        } else {
            log.error("{}：{}", message, e.getMessage(), e);
            throw new RedisOperationException(message, e);
        }
    }
}
