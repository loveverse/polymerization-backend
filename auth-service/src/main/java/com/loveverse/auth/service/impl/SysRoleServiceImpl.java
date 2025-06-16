package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.converter.SystemConverter;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.entity.SysRoleMenu;
import com.loveverse.auth.mapper.SysRoleMapper;
import com.loveverse.auth.mapper.SysRoleMenuMapper;
import com.loveverse.auth.request.SetRoleMenuDTO;
import com.loveverse.auth.request.SysRoleDTO;
import com.loveverse.auth.request.SysRolePageDTO;
import com.loveverse.auth.response.SysMenuVO;
import com.loveverse.auth.response.SysRoleVO;
import com.loveverse.auth.service.SysMenuService;
import com.loveverse.auth.service.SysRoleMenuService;
import com.loveverse.auth.service.SysRoleService;
import com.loveverse.auth.util.PageUtils;
import com.loveverse.core.dto.PageResult;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/17 20:02
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;
    private final SystemConverter systemConverter;
    private final SysMenuService sysMenuService;
    private final SysRoleMenuServiceImpl sysRoleMenuService;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public void createRole(SysRoleDTO sysRoleDTO) {
        SysRole sysRole = systemConverter.convertRoleToEntity(sysRoleDTO);
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void deleteRoles(List<Long> ids) {
        // TODO 使用 jackson 将 Long 类型转换为 String 到前端后，前端返回 String，会自动转换
        if (CollectionUtils.isEmpty(ids)) {
            throw new BadRequestException("请选择要删除的角色");
        }
        sysRoleMapper.delete(Wrappers.<SysRole>lambdaUpdate().in(SysRole::getId, ids));
    }

    @Override
    public void updateRole(SysRoleDTO sysRoleDTO) {
        SysRole data = sysRoleMapper.selectById(sysRoleDTO.getId());
        if (data == null) {
            throw new BadRequestException("不存在该角色");
        }
        sysRoleMapper.updateById(systemConverter.convertRoleToEntity(sysRoleDTO));
    }

    @Override
    public List<SysRoleVO> getRoleList(Integer status) {
        List<SysRole> systemRoles = sysRoleMapper.selectList(
                Wrappers.<SysRole>lambdaQuery().eq(status != null, SysRole::getStatus, status));
        return systemRoles.stream().map(systemConverter::convertRoleToVO).collect(Collectors.toList());
    }

    @Override
    public List<SysRoleVO> findRoleListByUserId(Long userId) {
        List<SysRole> roleList = sysRoleMapper.findRoleListByUserId(userId);
        return roleList.stream().map(systemConverter::convertRoleToVO).collect(Collectors.toList());
    }

    @Override
    public PageResult<SysRoleVO> getRolePage(SysRolePageDTO sysRolePageDTO) {
        IPage<SysRole> page = PageUtils.startPage(sysRolePageDTO);
        IPage<SysRole> roleIPage = sysRoleMapper.selectPage(page, Wrappers.lambdaQuery());
        return PageUtils.convert(roleIPage, systemConverter::convertRoleToVO);
    }

    @Override
    public List<SysRoleVO> roleListByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<SysRole> roleList = sysRoleMapper.selectList(Wrappers.<SysRole>lambdaQuery().in(SysRole::getId, roleIds));
        return roleList.stream().map(item -> {
            SysRoleVO sysRoleVO = new SysRoleVO();
            BeanUtils.copyProperties(item, sysRoleVO, "createTime", "updateTime");
            return sysRoleVO;
        }).collect(Collectors.toList());
    }

    @Override
    public SysRoleVO getMenuTreeByRoleId(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        if (sysRole == null) {
            throw new BadRequestException("不存在该记录");
        }
        SysRoleVO sysRoleVO = new SysRoleVO();
        BeanUtils.copyProperties(sysRole, sysRoleVO);
        if (sysRole.getRoleKey().equals("ROLE_ADMIN")) {
            List<SysMenuVO> menuVOS = sysMenuService.flatMenuListByModuleId(1L);
            List<Long> menuIds = menuVOS.stream().map(SysMenuVO::getId).distinct().collect(Collectors.toList());
            sysRoleVO.setMenuIds(menuIds);
        } else {
            List<Long> menuIds = sysRoleMenuService.getMenuIdsByRoleIds(Collections.singleton(roleId));
            sysRoleVO.setMenuIds(menuIds);
        }
        return sysRoleVO;
    }

    @Transactional
    @Override
    public void updateRolePermissions(SetRoleMenuDTO roleMenuDTO) {
        SysRoleVO roleVO = getMenuTreeByRoleId(roleMenuDTO.getId());
        if (roleVO.getRoleKey().equals("ROLE_ADMIN")) {
            throw new BadRequestException("无法修改admin的权限");
        }
        // 当前角色的权限[1,2,3]
        HashSet<Long> currentMenuIds = new HashSet<>(roleVO.getMenuIds());
        // 重新赋予的菜单[1,2,4]
        HashSet<Long> newMenuIds = new HashSet<>(roleMenuDTO.getMenuIds());
        if (currentMenuIds.equals(newMenuIds)) {
            return;
        }
        // 计算要删除的菜单id
        HashSet<Long> menuIdsToRemove = new HashSet<>(currentMenuIds);
        menuIdsToRemove.removeAll(newMenuIds);
        // 计算要增加的菜单id
        HashSet<Long> menuIdsToAdd = new HashSet<>(newMenuIds);
        menuIdsToAdd.removeAll(currentMenuIds);
        if (!menuIdsToRemove.isEmpty()) {
            sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>lambdaQuery().
                    eq(SysRoleMenu::getRoleId, roleVO.getId()).in(SysRoleMenu::getMenuId, menuIdsToRemove));
        }
        if (!menuIdsToAdd.isEmpty()) {
            List<SysRoleMenu> roleMenus = menuIdsToAdd.stream().map(item -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleVO.getId());
                sysRoleMenu.setMenuId(item);
                return sysRoleMenu;
            }).collect(Collectors.toList());
            sysRoleMenuService.saveBatch(roleMenus);
        }
    }
}
