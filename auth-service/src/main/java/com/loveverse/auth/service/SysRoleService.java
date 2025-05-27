package com.loveverse.auth.service;

import com.loveverse.auth.request.SysRoleDTO;
import com.loveverse.auth.request.SysRolePageDTO;
import com.loveverse.auth.response.SysRoleVO;
import com.loveverse.core.dto.PageResult;

import javax.validation.Valid;
import java.util.List;

/**
 * @author love
 * @since 2025/5/17 20:02
 */
public interface SysRoleService {

    void createRole(SysRoleDTO roleDto);

    void deleteRoles(List<Long> ids);

    void updateRole(SysRoleDTO sysRoleDto);

    List<SysRoleVO> getRoleList(Integer status);

    List<SysRoleVO> findRoleListByUserId(Long userId);

    PageResult<SysRoleVO> getRolePage(SysRolePageDTO sysRolePageDTO);


    List<SysRoleVO> roleListByRoleIds(List<Long> roleIds);
}
