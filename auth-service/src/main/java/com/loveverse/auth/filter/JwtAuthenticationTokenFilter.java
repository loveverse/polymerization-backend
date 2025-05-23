package com.loveverse.auth.filter;

import com.loveverse.auth.util.JwtTokenUtil;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断是否是 OPTIONS 预检请求
        //if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        //    filterChain.doFilter(request, response);
        //    return;
        //}
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        //JwtProperties jwtConfig = jwtTokenUtil.getJwtProperties();
        //String authToken = request.getHeader(jwtConfig.getHeader());
        //// 存在token且是以设定的标准前缀开头
        //if (StringUtils.hasText(authToken) && authToken.startsWith(jwtConfig.getPrefix())) {
        //    String token = authToken.substring(jwtConfig.getPrefix().length());
        //
        //    // 检查token是否在黑名单中
        //    //RBucket<String> blacklistBucket = redisUtils.getBucket("jwt:blacklist:" + token);
        //    //if (blacklistBucket.isExists()) {
        //    //    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has been invalidated");
        //    //    return;
        //    //}
        //    // 解析出用户id
        //    String userId = jwtTokenUtil.extractUserId(authToken);
        //    String key = "login:" + userId;
        //    LoginUserBO loginUser = (LoginUserBO) redisUtils.get(key);
        //    if(Objects.isNull(loginUser)){
        //        throw new UsernameNotFoundException("用户未登录");
        //    }
        //
        //
        //    //if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        //    //    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        //    //
        //    //    // 验证token有效性
        //    //    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        //    //        UsernamePasswordAuthenticationToken authentication =
        //    //                new UsernamePasswordAuthenticationToken(
        //    //                        userDetails, null, userDetails.getAuthorities());
        //    //        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //    //        SecurityContextHolder.getContext().setAuthentication(authentication);
        //    //    }
        //    //}
        //    // 将用户信息存入 SecurityContextHolder
        //    UsernamePasswordAuthenticationToken authenticationToken =
        //            new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        //    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //
        //}
        filterChain.doFilter(request, response);
    }
}
