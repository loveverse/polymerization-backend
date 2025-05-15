package com.loveverse.auth.config;

//import com.loveverse.auth.filter.JwtAuthenticationTokenFilter;

import com.alibaba.druid.support.json.JSONUtils;
import com.loveverse.auth.base.CustomAuthenticationProvider;
import com.loveverse.auth.filter.JwtAuthenticationTokenFilter;
import com.loveverse.auth.handler.AuthenticationEntryPointImpl;
import com.loveverse.auth.service.impl.UserDetailsServiceImpl;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.core.http.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Collections;

import static com.alibaba.druid.sql.ast.SQLPartitionValue.Operator.List;


/**
 * @author love
 * @since 2025/4/23
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;





    /**
     * BCryptPasswordEncoder 加密器,可以实现 PasswordEncoder 自定义密码加密校验
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份验证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }


    /**
     * 安全过滤器链配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource())) // 启用 CORS
                // 必须先禁用csrf才能使用antMatchers
                .csrf().disable()
                // 禁用默认 session，使用 token 设置无状态会话
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/**").permitAll()
                        //.antMatchers("/auth-api/auth/v1/login").permitAll()
                        //.antMatchers("/auth-api/auth/test").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable) // 禁用默认表单登录
                .httpBasic(AbstractHttpConfigurer::disable) // 禁用 Basic Auth
                .authenticationProvider(authenticationProvider())   // 自定义身份验证逻辑


                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // 通配符 Credentials 需设置为 false
        config.setAllowedOrigins(Collections.singletonList("*")); // 允许的前端地址
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允许的 HTTP 方法
        config.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求头
        //config.setAllowCredentials(true); // 需要凭证
        config.setMaxAge(18000L); // 预检请求缓存时间,单位是秒，当前为5小时
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有路径生效
        return source;
    }
}
