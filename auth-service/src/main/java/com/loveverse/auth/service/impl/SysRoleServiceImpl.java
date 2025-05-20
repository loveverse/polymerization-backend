package com.loveverse.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.mapper.SysRoleMapper;
import com.loveverse.auth.request.SysRoleReqDTO;
import com.loveverse.auth.response.SysRoleResVO;
import com.loveverse.auth.service.SysRoleService;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/17 20:02
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;

    @Override
    public void createRole(SysRoleReqDTO sysRoleReqDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleReqDTO, sysRole);
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void deleteRoles(Long[] ids) {
        sysRoleMapper.delete(Wrappers.<SysRole>update().lambda().in(SysRole::getId, CollUtil.toList(ids)));
    }

    @Override
    public void updateRole(SysRoleReqDTO sysRoleReqDTO) {
        SysRole data = sysRoleMapper.selectById(sysRoleReqDTO.getId());
        if (data == null) {
            throw new BadRequestException("不存在该角色");
        }
        BeanUtils.copyProperties(sysRoleReqDTO, data);
        sysRoleMapper.updateById(data);
    }

    @Override
    public List<SysRoleResVO> getRoleList() {
        List<SysRole> systemRoles = sysRoleMapper.selectList(Wrappers.emptyWrapper());
        return Optional.ofNullable(systemRoles).orElse(Collections.emptyList()).stream().map(item -> {
            SysRoleResVO sysRoleResVO = new SysRoleResVO();
            BeanUtils.copyProperties(item, sysRoleResVO);
            return sysRoleResVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SysRoleResVO> findRoleListByUserId(Long userId) {
        List<SysRole> roleList = sysRoleMapper.findRoleListByUserId(userId);
        return Optional.ofNullable(roleList).orElse(Collections.emptyList()).stream().map(item -> {
            SysRoleResVO sysRoleResVO = new SysRoleResVO();
            BeanUtils.copyProperties(item, sysRoleResVO);
            return sysRoleResVO;
        }).collect(Collectors.toList());
    }
}
