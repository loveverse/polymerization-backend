package com.loveverse.auth.handler;

import cn.hutool.json.JSONUtil;
import com.loveverse.core.http.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author love
 * @description 用来解决认证过的用户访问无权限资源时的异常
 * @since 2025/5/30 8:35
 */
@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.warn("无权限访问: {}", accessDeniedException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(ResponseCode.NOT_LOGIN.getResponse(accessDeniedException.getMessage())));
    }
}
