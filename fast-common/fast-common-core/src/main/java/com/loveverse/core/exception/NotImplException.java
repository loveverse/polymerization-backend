package com.loveverse.core.exception;

/**
 * @author love
 * @since 2025/5/13 15:40
 */
public class NotImplException extends BusinessException {
    public NotImplException(String message, Object... args) {
        super(message, args);
    }

    public NotImplException(String message) {
        super(message);
    }

    public NotImplException(String message, Throwable cause, Object... args) {
        super(message, cause, args);
    }

    public NotImplException(Throwable cause) {
        super(cause);
    }
}
