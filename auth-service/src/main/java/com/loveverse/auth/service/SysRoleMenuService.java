package com.loveverse.auth.service;

import java.util.List;
import java.util.Set;

/**
 * @author love
 * @since 2025/6/7 14:17
 */
public interface SysRoleMenuService {
    List<Long> getMenuIdsByRoleIds(Set<Long> roleIds);
}
