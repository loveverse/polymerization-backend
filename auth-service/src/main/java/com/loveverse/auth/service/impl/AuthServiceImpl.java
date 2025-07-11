package com.loveverse.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.loveverse.auth.bo.LoginUserBO;
import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.constant.RedisKeyConstant;
import com.loveverse.auth.password.CaptchaAuthenticationToken;
import com.loveverse.auth.request.UserLoginDTO;
import com.loveverse.auth.response.SysUserVO;
import com.loveverse.auth.response.UserLoginVO;
import com.loveverse.auth.service.AuthService;
import com.loveverse.auth.util.JwtTokenUtil;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

/**
 * @author love
 * @since 2025/4/28
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtProperties jwtProperties;
    private final RedisUtils redisUtils;

    @Override
    public UserLoginVO userLogin(UserLoginDTO user) {

        String password = decodeIfBase64(user.getPassword());
        // 获取得到用户名密码，封装成 Authentication 对象
        UsernamePasswordAuthenticationToken authenticationToken = new CaptchaAuthenticationToken(user.getUsername(), password, user.getCaptchaKey(), user.getCaptchaCode());
        // 调用 authenticationManager 的方法进行认证， Authentication 包含了登录用户信息和权限信息
        // 具体实现在 UserDetailsServiceImpl 重写 loadUserByUsername,查询数据库返回用户和权限
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 认证失败 SpringSecurity 会自动抛出 AuthenticationException 异常,不需要手动抛

        // 认证成功，通过 userId 生成 token
        LoginUserBO loginUser = (LoginUserBO) authenticate.getPrincipal();   // 获取用户信息
        Long userId = loginUser.getUser().getId();
        String token = jwtTokenUtil.generateToken(loginUser);
        // 将用户信息存到 redis，以userId作为key
        String key = RedisKeyConstant.build(RedisKeyConstant.LOGIN_ID, userId.toString());
        redisUtils.set(key, loginUser, jwtProperties.getExpireTime());
        // 返回用户信息、token、角色列表、权限菜单列表
        UserLoginVO loginInfoRes = new UserLoginVO();
        loginInfoRes.setToken(token);
        loginInfoRes.setTokenPrefix(jwtProperties.getPrefix());

        UserLoginVO.User userInfo = new UserLoginVO.User();
        userInfo.setId(userId);
        userInfo.setUserId(userId);
        userInfo.setUsername(loginUser.getUsername());
        loginInfoRes.setUser(userInfo);

        Date date = jwtTokenUtil.extractExpiration(token);
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        loginInfoRes.setExpireTime(localDateTime);
        return loginInfoRes;
    }

    @Override
    public void userLogout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserBO userBO = (LoginUserBO) authentication.getPrincipal();
        String key = RedisKeyConstant.build(RedisKeyConstant.LOGIN_ID, userBO.getUser().getId().toString());
        redisUtils.delete(key);
    }

    /**
     * 如果输入是 Base64 编码，则解码；否则返回原字符串
     */
    public String decodeIfBase64(String str) {
        if (!StringUtils.hasText(str)) {
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
