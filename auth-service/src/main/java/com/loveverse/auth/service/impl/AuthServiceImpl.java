package com.loveverse.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.dto.login.LoginInfoReq;
import com.loveverse.auth.dto.login.LoginInfoRes;
import com.loveverse.auth.dto.LoginUser;
import com.loveverse.auth.dto.login.SystemUserDto;
import com.loveverse.auth.service.AuthService;
import com.loveverse.auth.util.JwtTokenUtil;
import com.loveverse.redis.util.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author love
 * @since 2025/4/28
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public LoginInfoRes userLogin(LoginInfoReq loginInfoReq) {
        // 构造认证令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInfoReq.getUsername(), loginInfoReq.getPassword());
        // 对认证令牌进行校验，返回认证结果
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 存储认证信息到 SpringSecurity 上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // 获取用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String token = jwtTokenUtil.generateToken(loginUser);
        redisUtils.set("login:" + userId, loginUser, jwtProperties.getExpireTime());
        SystemUserDto systemUserDto = new SystemUserDto();
        BeanUtil.copyProperties(loginUser.getUser(), systemUserDto, "password");

        LoginInfoRes loginInfoRes = new LoginInfoRes();
        loginInfoRes.setUser(systemUserDto);
        loginInfoRes.setToken(token);
        return loginInfoRes;

    }
}
