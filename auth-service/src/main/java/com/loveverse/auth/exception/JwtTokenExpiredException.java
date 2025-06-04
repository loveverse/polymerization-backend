package com.loveverse.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author love
 * @since 2025/5/30 18:01
 */
public class JwtTokenExpiredException extends AuthenticationException {
    public JwtTokenExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
