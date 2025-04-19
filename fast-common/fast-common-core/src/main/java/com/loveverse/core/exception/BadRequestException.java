package com.loveverse.core.exception;

public class BadRequestException extends BusinessException {

    public BadRequestException(String message, Object... args) {
        super(message, args);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}