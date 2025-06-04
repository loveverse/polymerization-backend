package com.loveverse.auth.handler;

import cn.hutool.json.JSONUtil;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.core.http.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import sun.security.util.Debug;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author love
 * @description 用来解决匿名用户访问无权限资源时的异常
 * @since 2025/5/14 17:38
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.warn("用户未登录:{}", authException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(ResponseCode.NOT_LOGIN.getResponse(authException.getMessage())));
    }
}
