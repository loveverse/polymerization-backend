package com.loveverse.oss.thirdparty.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {
    public BusinessException(String message, Object... args) {
        super(String.format(replacePlaceholders(message), args));
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    // Helper method to replace placeholders
    private static String replacePlaceholders(String message) {
        return message.replace("{}", "%s");
    }
}
