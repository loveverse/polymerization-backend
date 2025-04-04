package com.loveverse.oss.thirdparty.exception;

public abstract class FileException extends BusinessException {
    public FileException(String message, Object... args) {
        super(message, args);
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

}