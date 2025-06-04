package com.loveverse.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author love
 * @since 2025/5/30 18:01
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
