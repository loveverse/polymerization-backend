package com.loveverse.auth.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

/**
 * @author love
 * @since 2025/5/12 10:28
 */
public class PasswordEncoderUtil implements PasswordEncoder {
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        // 重写加密逻辑，使用md5加密
        return Arrays.toString(DigestUtils.md5Digest(rawPassword.toString().getBytes()));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
