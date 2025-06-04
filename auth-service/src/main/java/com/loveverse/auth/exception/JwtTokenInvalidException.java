package com.loveverse.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author love
 * @since 2025/5/30 18:01
 */
public class JwtTokenInvalidException extends AuthenticationException {
    public JwtTokenInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtTokenInvalidException(String msg) {
        super(msg);
    }
}
