package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loveverse.auth.dto.LoginUser;
import com.loveverse.auth.entity.SystemUser;
import com.loveverse.auth.mapper.MenuMapper;
import com.loveverse.auth.mapper.UserMapper;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author love
 * @description 重写默认从内存中查找用户信息
 * @since 2025/4/23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Ordered {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userMapper.selectOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUserName, username));
        if (Objects.isNull(user)) {
            throw new BadRequestException("用户不存在");
        }
        // Todo 查询菜单权限列表
        //List<String> list =  menuMapper.selectPermissionByUserId(user.getId());
        // 将数据封装成 UserDetails 返回
        return new LoginUser(user, Collections.emptyList());
    }

    @Override
    public int getOrder() {
        // 最高优先级
        return 0;
    }
}
