package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loveverse.auth.dto.LoginUser;
import com.loveverse.auth.entity.SystemUser;
import com.loveverse.auth.mapper.MenuMapper;
import com.loveverse.auth.mapper.UserMapper;
import com.loveverse.auth.service.SystemUserDetailsService;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.redis.annotation.Cacheable;
import com.loveverse.redis.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author love
 * @since 2025/4/23
 */
@Service
@RequiredArgsConstructor
public class SystemUserDetailsServiceImpl implements SystemUserDetailsService {

    private final UserMapper userMapper;

    private final MenuMapper menuMapper;

    private final RedisUtils redisUtils;


    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SystemUser systemUser = userMapper.selectOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUserName, username));
        if (Objects.isNull(systemUser)) {
            throw new BadRequestException("用户名或密码错误");
        }
        List<String> list = menuMapper.selectPermissionByUserId(systemUser.getId());
        return new LoginUser(systemUser, list);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
