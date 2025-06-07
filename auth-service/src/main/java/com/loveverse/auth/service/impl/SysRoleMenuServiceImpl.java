package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loveverse.auth.entity.SysRoleMenu;
import com.loveverse.auth.mapper.SysRoleMenuMapper;
import com.loveverse.auth.service.SysRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/6/7 14:17
 */
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<Long> getMenuIdsByRoleIds(Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery().in(SysRoleMenu::getRoleId, roleIds));
        return sysRoleMenus.stream().map(SysRoleMenu::getMenuId).distinct().collect(Collectors.toList());
    }
}
