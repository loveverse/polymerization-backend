package com.loveverse.auth.base;


import com.loveverse.auth.constant.RedisKeyConstant;
import com.loveverse.auth.password.CaptchaAuthenticationToken;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author love
 * @since 2025/5/15 10:22
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtils redisUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (authentication instanceof CaptchaAuthenticationToken) {
            String captchaKey = ((CaptchaAuthenticationToken) authentication).getCaptchaKey();
            String captchaCode = ((CaptchaAuthenticationToken) authentication).getCaptchaCode();
            String key = RedisKeyConstant.build(RedisKeyConstant.CAPTCHA_UUID, captchaKey);
            String storedCode = redisUtils.get(key);
            if (storedCode == null || !storedCode.equalsIgnoreCase(captchaCode)) {
                throw new BadRequestException("验证码错误或已过期");
            }
            // 验证成功后删除
            redisUtils.delete(key);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 支持 UsernamePassword 和 Captcha 两种认证方式
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) ||
                CaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
