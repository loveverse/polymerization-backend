package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.bo.LoginUserBO;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.entity.SysUserRole;
import com.loveverse.auth.mapper.SysRoleMapper;
import com.loveverse.auth.mapper.SysUserMapper;
import com.loveverse.auth.mapper.SysUserRoleMapper;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author love
 * @description 重写默认从内存中查找用户信息
 * @since 2025/4/23
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, Ordered {

    private final SysUserMapper userMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (user == null) {
            throw new BadRequestException("用户不存在");
        }
        if (!Objects.equals(user.getStatus(), 1)) {
            throw new BadRequestException("用户已被停用，请联系管理员");
        }

        // TODO 查询角色权限标识，通常是一个字符串形式的权限标识符。例如，"ROLE_ADMIN" 或 "READ_USER"
        List<SysRole> roleList = getUserAuthorities(user.getId());
        // 将数据封装成 UserDetails 返回
        return new LoginUserBO(user, roleList);
    }

    @Override
    public int getOrder() {
        // 最高优先级
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 获取用户权限集合
     */
    private List<SysRole> getUserAuthorities(Long userId) {
        // 查询用户角色关联
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(
                Wrappers.<SysUserRole>lambdaQuery()
                        .eq(SysUserRole::getUserId, userId));

        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }

        // 查询角色信息
        List<Long> roleIds = userRoles.stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        return sysRoleMapper.selectList(
                Wrappers.<SysRole>lambdaQuery()
                        .in(SysRole::getId, roleIds));
    }
}
