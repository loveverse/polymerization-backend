package com.loveverse.core.exception;

/**
 * @author love
 * @since 2025/5/13 15:35
 */
public class NotLoginException extends BusinessException {
    public NotLoginException(String message, Object... args) {
        super(message, args);
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(String message, Throwable cause, Object... args) {
        super(message, cause, args);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }
}
