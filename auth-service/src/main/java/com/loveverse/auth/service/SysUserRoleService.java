package com.loveverse.auth.service;

import com.loveverse.auth.entity.SysUserRole;

import java.util.List;

/**
 * @author love
 * @since 2025/5/27 17:49
 */
public interface SysUserRoleService {
    List<SysUserRole> getUserRoles(List<Long> userIds);
}
