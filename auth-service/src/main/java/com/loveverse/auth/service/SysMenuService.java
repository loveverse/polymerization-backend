package com.loveverse.auth.service;

import com.loveverse.auth.request.SysMenuDTO;
import com.loveverse.auth.response.SysMenuVO;

import java.util.List;

/**
 * @author love
 * @since 2025/6/4 18:19
 */
public interface SysMenuService {
    void createMenu(SysMenuDTO sysMenuDTO);

    void deleteMenu(Long id, Long moduleId);

    void updateMenu(SysMenuDTO sysMenuDTO);

    List<SysMenuVO> getMenuList();

    List<SysMenuVO> getMenuTreeByModuleId(Long moduleId);

    List<SysMenuVO> flatMenuListByModuleId(Long moduleId);

    List<SysMenuVO> getMenuTreeByRoleIds(List<Long> roleIds);
}
