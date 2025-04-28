package com.loveverse.auth.filter;

import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.util.JwtTokenUtil;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author love
 * @since 2025/4/28
 */
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtProperties jwtConfig = jwtTokenUtil.getJwtProperties();
        String authToken = request.getHeader(jwtConfig.getHeader());
        // 存在token且是以设定的标准前缀开头
        if (StringUtils.hasText(authToken) && authToken.startsWith(jwtConfig.getPrefix())) {
            String token = authToken.substring(jwtConfig.getPrefix().length());

            // 检查token是否在黑名单中
            //RBucket<String> blacklistBucket = redisUtils.getBucket("jwt:blacklist:" + token);
            //if (blacklistBucket.isExists()) {
            //    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has been invalidated");
            //    return;
            //}
            // 解析出用户id
            String userId = jwtTokenUtil.extractUserId(authToken);
            //
            //if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //
            //    // 验证token有效性
            //    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            //        UsernamePasswordAuthenticationToken authentication =
            //                new UsernamePasswordAuthenticationToken(
            //                        userDetails, null, userDetails.getAuthorities());
            //        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //        SecurityContextHolder.getContext().setAuthentication(authentication);
            //    }
            //}
        }
        filterChain.doFilter(request, response);
    }
}
