package com.loveverse.redis.util;

import com.loveverse.redis.exception.RedisOperationException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.*;
import org.redisson.client.RedisClient;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author love
 * @since 2025/4/24
 */
@Component
@RequiredArgsConstructor
public class RedisUtils {
    private final RedissonClient redissonClient;

    // ============================== Common ==============================

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     */
    public <T> void set(String key, T value, long time) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            if (time > 0) {
                bucket.set(value, time, TimeUnit.SECONDS);
            } else {
                bucket.set(value);
            }
        } catch (Exception e) {
            throw new RedisOperationException("Redis set操作失败", e);
        }
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public <T> T get(String key) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            return bucket.get();
        } catch (Exception e) {
            throw new RedisOperationException("Redis get操作失败", e);
        }
    }

    /**
     * 删除缓存
     * @param keys 可以传一个或多个key
     */
    public void delete(String... keys) {
        try {
            if (keys == null || keys.length == 0) {
                return;
            }
            if (keys.length == 1) {
                redissonClient.getBucket(keys[0]).delete();
            } else {
                redissonClient.getKeys().delete(keys);
            }
        } catch (Exception e) {
            throw new RedisOperationException("Redis delete操作失败", e);
        }
    }

    // ============================== Lock ==============================

    /**
     * 获取分布式锁
     * @param lockKey 锁key
     * @param waitTime 等待时间(秒)
     * @param leaseTime 持有时间(秒)
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RedisOperationException("获取分布式锁被中断", e);
        } catch (Exception e) {
            throw new RedisOperationException("获取分布式锁失败", e);
        }
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁key
     */
    public void unlock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Exception e) {
            throw new RedisOperationException("释放分布式锁失败", e);
        }
    }

    // ============================== Map ==============================

    public <K, V> void hset(String key, K field, V value) {
        try {
            RMap<K, V> map = redissonClient.getMap(key);
            map.put(field, value);
        } catch (Exception e) {
            throw new RedisOperationException("Redis hset操作失败", e);
        }
    }

    public <K, V> V hget(String key, K field) {
        try {
            RMap<K, V> map = redissonClient.getMap(key);
            return map.get(field);
        } catch (Exception e) {
            throw new RedisOperationException("Redis hget操作失败", e);
        }
    }

    // ============================== List ==============================

    public <T> void lpush(String key, T value) {
        try {
            RDeque<T> deque = redissonClient.getDeque(key);
            deque.push(value);
        } catch (Exception e) {
            throw new RedisOperationException("Redis lpush操作失败", e);
        }
    }

    public <T> T rpop(String key) {
        try {
            RDeque<T> deque = redissonClient.getDeque(key);
            return deque.pollLast();
        } catch (Exception e) {
            throw new RedisOperationException("Redis rpop操作失败", e);
        }
    }

    // ============================== Set ==============================

    public <T> void sadd(String key, T... members) {
        try {
            RSet<T> set = redissonClient.getSet(key);
            set.addAll(Arrays.asList(members));
        } catch (Exception e) {
            throw new RedisOperationException("Redis sadd操作失败", e);
        }
    }

    public <T> Set<T> smembers(String key) {
        try {
            RSet<T> set = redissonClient.getSet(key);
            return set.readAll();
        } catch (Exception e) {
            throw new RedisOperationException("Redis smembers操作失败", e);
        }
    }

    // ============================== 高级功能 ==============================

    /**
     * 批量获取缓存
     */
    public <T> Map<String, T> batchGet(Collection<String> keys) {
        try {
            RBuckets buckets = redissonClient.getBuckets();
            return buckets.get(keys.toArray(new String[0]));
        } catch (Exception e) {
            throw new RedisOperationException("Redis批量get操作失败", e);
        }
    }

    /**
     * 批量设置缓存
     */
    public <T> void batchSet(Map<String, T> keyValueMap, long time) {
        try {
            RBuckets buckets = redissonClient.getBuckets();
            //if (time > 0) {
            //
            //    buckets.expire(keyValueMap, time, TimeUnit.SECONDS);
            //} else {
            //    buckets.set(keyValueMap);
            //}
        } catch (Exception e) {
            throw new RedisOperationException("Redis批量set操作失败", e);
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
            throw new RedisOperationException("Redis事务执行失败", e);
        }
    }

    @FunctionalInterface
    public interface TransactionalTask<T> {
        T execute(RTransaction transaction) throws Exception;
    }

}
