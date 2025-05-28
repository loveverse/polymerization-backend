package com.loveverse.auth.filter;

import com.loveverse.auth.bo.LoginUserBO;
import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.constant.RedisKeyConstant;
import com.loveverse.auth.util.JwtTokenUtil;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author love
 * @since 2025/4/28
 */
@RequiredArgsConstructor
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtProperties jwtProperties;

    private final RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中token（Authorization）字段
        String authToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(authToken)) {
            // 兼容前端没携带 Bearer 前缀
            String token = authToken.replace(jwtProperties.getPrefix(), "").trim();
            // 解析出用户id
            String userId = jwtTokenUtil.extractUserId(token);
            String key = RedisKeyConstant.build(RedisKeyConstant.LOGIN_ID, userId);
            LoginUserBO loginUser = redisUtils.get(key);
            if (Objects.isNull(loginUser)) {
                throw new UsernameNotFoundException("用户未登录");
            }
            // 将用户信息存入 SecurityContextHolder
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);
    }
}
