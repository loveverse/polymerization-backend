package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysMenu;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.entity.SysRoleMenu;
import com.loveverse.auth.mapper.SysMenuMapper;
import com.loveverse.auth.mapper.SysRoleMapper;
import com.loveverse.auth.mapper.SysRoleMenuMapper;
import com.loveverse.auth.request.SysMenuDTO;
import com.loveverse.auth.response.SysMenuVO;
import com.loveverse.auth.service.SysMenuService;
import com.loveverse.auth.util.TreeUtils;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/6/4 18:19
 */
@RequiredArgsConstructor
@Service
//
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysRoleMapper sysRoleMapper;


    @Override
    public void createMenu(SysMenuDTO sysMenuDTO) {
        Long parentId = sysMenuDTO.getParentId();
        if (parentId != null && !parentId.equals(0L)) {
            SysMenu menu = sysMenuMapper.selectById(parentId);
            // 没有找到父菜单
            if (menu == null) {
                throw new BadRequestException("没有找到父菜单");
            }
        }
        // 没有传默认是根节点下
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO, sysMenu);
        sysMenuMapper.insert(sysMenu);
    }

    @Transactional
    @Override
    public void deleteMenu(Long id, Long moduleId) {
        // 查询父菜单为当前菜单id的菜单列表
        List<SysMenu> menus = sysMenuMapper.selectList(Wrappers.<SysMenu>lambdaQuery()
                .eq(SysMenu::getModuleId, moduleId)
                .eq(SysMenu::getParentId, id));
        if (!CollectionUtils.isEmpty(menus)) {
            throw new BadRequestException("菜单存在下级节点，删除失败");
        }
        // 删除角色 菜单 关联表数据
        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getMenuId, id));
        // 删除菜单
        sysMenuMapper.deleteById(id);
    }

    @Override
    public void updateMenu(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = sysMenuMapper.selectById(sysMenuDTO.getId());
        if (sysMenu == null) {
            throw new BadRequestException("不存在该记录");
        }
        BeanUtils.copyProperties(sysMenuDTO, sysMenu);
        sysMenuMapper.update(sysMenu, Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getId, sysMenu.getId()));
    }

    @Override
    public List<SysMenuVO> getMenuList() {
        return Collections.emptyList();
    }

    @Override
    public List<SysMenuVO> getMenuTreeByModuleId(Long moduleId) {
        List<SysMenu> menus = sysMenuMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getModuleId, moduleId));
        List<SysMenuVO> menuVOS = menus.stream().map(item -> {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(item, sysMenuVO);
            return sysMenuVO;
        }).collect(Collectors.toList());
        // 创建菜单没带 parentId 默认为 0
        return TreeUtils.buildTree(menuVOS, SysMenuVO::getId, SysMenuVO::getParentId, SysMenuVO::setChildren, 0L);
    }

    @Override
    public List<SysMenuVO> flatMenuListByModuleId(Long moduleId) {
        List<SysMenu> sysMenus = sysMenuMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getModuleId, moduleId));
        return sysMenus.stream().map(item -> {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(item, sysMenuVO);
            return sysMenuVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SysMenuVO> getMenuTreeByRoleIds(List<Long> roleIds) {
        // 这里不报错，因为是直接给 AuthController 调用
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<SysRole> roleList = sysRoleMapper.selectList(Wrappers.<SysRole>lambdaQuery().in(SysRole::getId, roleIds));
        Set<String> keys = roleList.stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
        // 默认为后台管理模块，其他模块后续可以考虑扩展实现
        LambdaQueryWrapper<SysMenu> queryWrapper = Wrappers.<SysMenu>lambdaQuery()
                .eq(SysMenu::getModuleId, 1);
        // 不是管理员查自己的
        if (!keys.contains("ROLE_ADMIN")) {
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery().in(SysRoleMenu::getRoleId, roleIds));
            List<Long> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
            queryWrapper.in(SysMenu::getId, menuIds);
        }
        List<SysMenu> sysMenus = sysMenuMapper.selectList(queryWrapper);
        return sysMenus.stream().map(item -> {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(item, sysMenuVO);
            return sysMenuVO;
        }).collect(Collectors.toList());
    }
}
