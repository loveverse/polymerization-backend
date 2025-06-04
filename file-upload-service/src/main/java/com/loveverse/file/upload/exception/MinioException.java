package com.loveverse.file.upload.exception;

import com.loveverse.core.exception.ServerException;

/**
 * @author love
 * @since 2025/6/3 9:47
 */
public class MinioException extends ServerException {
    public MinioException(String message, Object... args) {
        super(message, args);
    }

    public MinioException(String message) {
        super(message);
    }

    public MinioException(String message, Throwable cause, Object... args) {
        super(message, cause, args);
    }

    public MinioException(Throwable cause) {
        super(cause);
    }
}
