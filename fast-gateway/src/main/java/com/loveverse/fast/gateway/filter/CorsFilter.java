package com.loveverse.fast.gateway.filter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class CorsFilter implements WebFilter {

    // 重写 Spring 框架中 Spring Web 模块跨域配置
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 如果不是跨域请求，直接放行
        if (!CorsUtils.isCorsRequest(request)) {
            return chain.filter(exchange);
        }

        HttpHeaders requestHeaders = request.getHeaders();                                  // 获取请求头
        ServerHttpResponse response = exchange.getResponse();                                    // 获取响应对象
        HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();          // 获取请求方式对象
        HttpHeaders headers = response.getHeaders();                                        // 获取响应头

        // 添加允许的源
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());

        // 添加允许的头信息
        headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());

        // 如果有请求方式，添加允许的请求方式
        if (requestMethod != null) {
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
        }

        // 允许携带凭证（如cookie）
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        // 允许暴露的头信息
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");

        // 设置本次跨域检测的有效期,加L预检缓存时间不生效
        headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "18000");

        // 如果是OPTIONS请求，直接返回OK
        if (request.getMethod() == HttpMethod.OPTIONS) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }

        // 其他请求放行
        return chain.filter(exchange);
    }
}
