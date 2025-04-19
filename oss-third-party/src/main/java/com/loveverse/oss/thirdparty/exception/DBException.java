package com.loveverse.oss.thirdparty.exception;


import com.loveverse.core.exception.ServerException;

/**
 * @author love
 * @since 2025/4/9
 */
public class DBException extends ServerException {
    public DBException(String message, Object... args) {
        super(message, args);
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
}
