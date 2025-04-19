package com.loveverse.fast.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author love
 * @since 2025/4/17
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 获取响应
        ServerHttpResponse response = exchange.getResponse();
        // 如果是登录请求则放行
        if(request.getURI().getPath().contains("/admin/login")){
            return chain.filter(exchange);
        }
        // 获取请求头
        HttpHeaders headers = request.getHeaders();
        // 获取请求头令牌
        String token = headers.getFirst("token");
        // 请求头没有令牌
        if(!StringUtils.hasText(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
