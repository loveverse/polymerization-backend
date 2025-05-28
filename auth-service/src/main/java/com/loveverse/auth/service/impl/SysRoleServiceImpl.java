package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.converter.SystemConverter;
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
    private final SystemConverter systemConverter;

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
        return Optional.ofNullable(systemRoles).orElse(Collections.emptyList())
                .stream().map(systemConverter::convertRoleToVO).collect(Collectors.toList());
    }

    @Override
    public List<SysRoleVO> findRoleListByUserId(Long userId) {
        List<SysRole> roleList = sysRoleMapper.findRoleListByUserId(userId);
        return Optional.ofNullable(roleList).orElse(Collections.emptyList())
                .stream().map(systemConverter::convertRoleToVO).collect(Collectors.toList());
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
        return Optional.ofNullable(roleList).orElse(Collections.emptyList()).stream().map(item -> {
            SysRoleVO sysRoleVO = new SysRoleVO();
            BeanUtils.copyProperties(item, sysRoleVO, "createTime", "updateTime", "status");
            return sysRoleVO;
        }).collect(Collectors.toList());
    }
}
