package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loveverse.auth.entity.SystemUser;
import com.loveverse.auth.mapper.UserMapper;
import com.loveverse.auth.service.SystemUserDetailsService;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.redis.annotation.Cacheable;
import org.redisson.client.RedisClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author love
 * @since 2025/4/23
 */
@Service
public class SystemUserDetailsServiceImpl implements SystemUserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisClient redisClient;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SystemUser systemUser = userMapper.selectOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUserName, username));
        if (Objects.isNull(systemUser)) {
            throw new BadRequestException("用户名或密码错误");
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
