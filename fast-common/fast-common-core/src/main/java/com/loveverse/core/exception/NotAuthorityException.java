package com.loveverse.core.exception;

/**
 * @author love
 * @since 2025/5/13 15:43
 */
public class NotAuthorityException extends BusinessException {
    public NotAuthorityException(String message, Object... args) {
        super(message, args);
    }

    public NotAuthorityException(String message) {
        super(message);
    }

    public NotAuthorityException(String message, Throwable cause, Object... args) {
        super(message, cause, args);
    }

    public NotAuthorityException(Throwable cause) {
        super(cause);
    }
}
