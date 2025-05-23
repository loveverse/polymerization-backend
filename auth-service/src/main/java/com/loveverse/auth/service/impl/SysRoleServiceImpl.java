package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.mapper.SysRoleMapper;
import com.loveverse.auth.request.SysRoleDTO;
import com.loveverse.auth.request.SysRolePageDTO;
import com.loveverse.auth.response.SysRoleVO;
import com.loveverse.auth.service.SysRoleService;
import com.loveverse.auth.util.PageUtils;
import com.loveverse.core.dto.PageResult;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    @Override
    public void createRole(SysRoleDTO sysRoleReqDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleReqDTO, sysRole);
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
    public void updateRole(SysRoleDTO sysRoleReqDTO) {
        SysRole data = sysRoleMapper.selectById(sysRoleReqDTO.getId());
        if (data == null) {
            throw new BadRequestException("不存在该角色");
        }
        BeanUtils.copyProperties(sysRoleReqDTO, data);
        sysRoleMapper.updateById(data);
    }

    @Override
    public List<SysRoleVO> getRoleList() {
        List<SysRole> systemRoles = sysRoleMapper.selectList(Wrappers.lambdaQuery());
        return Optional.ofNullable(systemRoles).orElse(Collections.emptyList()).stream().map(item -> {
            SysRoleVO sysRoleResVO = new SysRoleVO();
            BeanUtils.copyProperties(item, sysRoleResVO);
            return sysRoleResVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SysRoleVO> findRoleListByUserId(Long userId) {
        List<SysRole> roleList = sysRoleMapper.findRoleListByUserId(userId);
        return Optional.ofNullable(roleList).orElse(Collections.emptyList()).stream().map(item -> {
            SysRoleVO sysRoleResVO = new SysRoleVO();
            BeanUtils.copyProperties(item, sysRoleResVO);
            return sysRoleResVO;
        }).collect(Collectors.toList());
    }

    @Override
    public PageResult<SysRoleVO> getRolePage(SysRolePageDTO sysRolePageDTO) {
        IPage<SysRole> page = PageUtils.startPage(sysRolePageDTO);
        IPage<SysRole> roleIPage = sysRoleMapper.selectPage(page, Wrappers.lambdaQuery());
        return PageUtils.convert(roleIPage, item -> {
            SysRoleVO sysRoleVO = new SysRoleVO();
            BeanUtils.copyProperties(item, sysRoleVO);
            return sysRoleVO;
        });
    }
}
