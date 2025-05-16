package com.loveverse.auth.handler;

import com.loveverse.core.exception.BadRequestException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author love
 * @since 2025/5/14 17:38
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //response.getWriter().write(JSONUtils.toJSONString(ResponseCode.BAD_REQUEST.getResponse("密码错误")));
        throw new BadRequestException("密码错误");
    }
}
