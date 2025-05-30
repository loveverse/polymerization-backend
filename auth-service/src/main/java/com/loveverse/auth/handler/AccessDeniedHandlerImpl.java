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
 * @since 2025/5/30 8:35
 * @description 用户尝试访问受保护资源时出现的身份异常，在身份验证失败时被调用
 */
@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn(accessDeniedException.getMessage(),"888");
        response.getWriter().write(JSONUtil.toJsonStr(ResponseCode.BAD_REQUEST.getResponse(accessDeniedException.getMessage())));
    }
}
