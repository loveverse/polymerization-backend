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
import com.loveverse.core.http.ResponseCode;
import com.loveverse.redis.util.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Collections;
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
        String password = decodeIfBase64(user.getPassword());
        // 获取得到用户名密码，封装成 Authentication 对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), password);
        // 调用 authenticationManager 的方法进行认证， Authentication 包含了登录用户信息和权限信息
        // 具体实现在 UserDetailsServiceImpl 重写 loadUserByUsername,查询数据库返回用户和权限
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 认证失败 SpringSecurity 会自动抛出 AuthenticationException 异常,不需要手动抛

        // 认证成功，通过 userId 生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();   // 获取用户信息
        //authenticate.getAuthorities()
        String userId = loginUser.getUser().getId().toString();
        String token = jwtTokenUtil.generateToken(loginUser);
        // 将用户信息存到 redis
        redisUtils.set("login:" + userId, loginUser, jwtTokenUtil.getJwtProperties().getExpireTime());
        // 返回用户信息、token、角色列表、权限菜单列表
        SystemUserDto systemUserDto = new SystemUserDto();
        BeanUtil.copyProperties(loginUser.getUser(), systemUserDto, "password");
        LoginInfoRes loginInfoRes = new LoginInfoRes();
        loginInfoRes.setUser(systemUserDto);
        loginInfoRes.setToken(token);
        loginInfoRes.setMenus(Collections.emptyList());
        loginInfoRes.setRoles(Collections.emptyList());
        return loginInfoRes;
    }

    /**
     * 如果输入是 Base64 编码，则解码；否则返回原字符串
     */
    public String decodeIfBase64(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        try {
            // 尝试解码（兼容标准 Base64 和 URL 安全 Base64）
            byte[] decodedBytes = Base64.getDecoder().decode(str);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            // 解码失败，说明不是 Base64，返回原字符串
            return str;
        }
    }

}
