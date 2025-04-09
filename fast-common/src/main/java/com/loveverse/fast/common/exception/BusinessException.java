package com.loveverse.fast.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author love
 * @since 2025/4/3
 */
@Slf4j
public abstract class BusinessException extends RuntimeException {

    public BusinessException(String message, Object... args) {
        super(formatMessage(message, args));
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause, Object... args) {
        super(formatMessage(message, args), cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    private static String formatMessage(String message, Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        // 替换占位符 {} 为 %s
        String replaced = message.replace("{}", "%s");
        return String.format(replaced, args);
    }
}
