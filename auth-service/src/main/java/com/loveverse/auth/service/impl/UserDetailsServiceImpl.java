package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loveverse.auth.bo.LoginUserBO;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.mapper.SysMenuMapper;
import com.loveverse.auth.mapper.SysUserMapper;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * @author love
 * @description 重写默认从内存中查找用户信息
 * @since 2025/4/23
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, Ordered {

    private final SysUserMapper userMapper;
    private final SysMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, username));
        if (user == null) {
            throw new BadRequestException("用户不存在");
        }
        // TODO 查询菜单权限列表
        //List<String> list =  menuMapper.selectPermissionByUserId(user.getId());
        // 将数据封装成 UserDetails 返回
        return new LoginUserBO(user, Collections.emptyList());
    }

    @Override
    public int getOrder() {
        // 最高优先级
        return 0;
    }
}
