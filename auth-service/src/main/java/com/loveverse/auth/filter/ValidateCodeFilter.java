package com.loveverse.auth.filter;

import com.loveverse.auth.constant.SecurityConstant;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author love
 * @since 2025/5/9 14:43
 */
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if(!SecurityConstant.LOGIN_URL.equals(servletPath)){
            filterChain.doFilter(request,response);
        }
    }
}
