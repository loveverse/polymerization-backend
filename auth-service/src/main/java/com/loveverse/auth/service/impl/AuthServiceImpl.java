package com.loveverse.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.dto.login.LoginInfoReq;
import com.loveverse.auth.dto.login.LoginInfoRes;
import com.loveverse.auth.dto.LoginUser;
import com.loveverse.auth.dto.login.SystemUserDto;
import com.loveverse.auth.service.AuthService;
import com.loveverse.auth.util.JwtTokenUtil;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.redis.util.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author love
 * @since 2025/4/28
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public LoginInfoRes userLogin(LoginInfoReq user) {
        // AuthenticationManager authenticate 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 对认证令牌进行校验，返回认证结果
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new BadRequestException("登录失败");
        }

        // 获取用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = jwtTokenUtil.generateToken(loginUser);
        redisUtils.set("login:" + userId, loginUser, jwtTokenUtil.getJwtProperties().getExpireTime());
        SystemUserDto systemUserDto = new SystemUserDto();
        BeanUtil.copyProperties(loginUser.getUser(), systemUserDto, "password");
        LoginInfoRes loginInfoRes = new LoginInfoRes();
        loginInfoRes.setUser(systemUserDto);
        loginInfoRes.setToken(token);
        return loginInfoRes;

    }
}
