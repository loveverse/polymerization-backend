package com.loveverse.auth.password;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author love
 * @since 2025/5/16 17:03
 */
@Getter
public class CaptchaAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String captchaKey;

    private final String captchaCode;

    public CaptchaAuthenticationToken(Object principal, Object credentials, String captchaKey, String captchaCode) {
        super(principal, credentials);
        this.captchaKey = captchaKey;
        this.captchaCode = captchaCode;
    }

    public CaptchaAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String captchaKey, String captchaCode) {
        super(principal, credentials, authorities);
        this.captchaKey = captchaKey;
        this.captchaCode = captchaCode;
    }

    @Override
    public Object getCredentials() {
        return super.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return super.getPrincipal();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        super.setAuthenticated(isAuthenticated);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
