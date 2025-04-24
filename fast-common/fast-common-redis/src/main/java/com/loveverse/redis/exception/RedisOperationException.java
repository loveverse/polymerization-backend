package com.loveverse.redis.exception;

/**
 * @author love
 * @since 2025/4/24
 */
public class RedisOperationException extends RuntimeException{
    public RedisOperationException(String message) {
        super(message);
    }

    public RedisOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
