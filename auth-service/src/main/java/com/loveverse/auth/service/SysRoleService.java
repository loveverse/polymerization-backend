package com.loveverse.auth.service;

import com.loveverse.auth.request.SysRoleReqDTO;
import com.loveverse.auth.response.SysRoleResVO;

import java.util.List;

/**
 * @author love
 * @since 2025/5/17 20:02
 */
public interface SysRoleService {

    void createRole(SysRoleReqDTO roleDto);

    void deleteRoles(Long[] ids);

    void updateRole(SysRoleReqDTO sysRoleDto);

    List<SysRoleResVO> getRoleList();

    List<SysRoleResVO> findRoleListByUserId(Long userId);
}
