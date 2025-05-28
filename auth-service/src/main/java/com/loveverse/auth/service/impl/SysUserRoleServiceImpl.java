package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.entity.SysUserRole;
import com.loveverse.auth.mapper.SysUserRoleMapper;
import com.loveverse.auth.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author love
 * @since 2025/5/27 17:48
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public List<SysUserRole> getUserRoles(List<Long> userIds) {
        return this.list(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getUserId, userIds));
    }
}
