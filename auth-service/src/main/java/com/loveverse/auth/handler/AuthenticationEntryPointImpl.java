package com.loveverse.auth.handler;

import cn.hutool.json.JSONUtil;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.core.http.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author love
 * @since 2025/5/14 17:38
 * @description 访问一个需要认证的 URL 资源，但是此时自己尚未认证（登录）的情况下,调用此类
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn(authException.getMessage(),"888");
        response.getWriter().write(JSONUtil.toJsonStr(ResponseCode.BAD_REQUEST.getResponse(authException.getMessage())));
    }
}
