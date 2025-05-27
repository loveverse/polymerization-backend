package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.entity.SysUserRole;
import com.loveverse.auth.mapper.SysUserMapper;
import com.loveverse.auth.mapper.SysUserRoleMapper;
import com.loveverse.auth.request.SysUserDTO;
import com.loveverse.auth.request.SysUserPageDTO;
import com.loveverse.auth.response.SysRoleVO;
import com.loveverse.auth.response.SysUserVO;
import com.loveverse.auth.service.SysRoleService;
import com.loveverse.auth.service.SysUserService;
import com.loveverse.auth.util.PageUtils;
import com.loveverse.core.dto.PageResult;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.mybatis.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/19 18:00
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    // 如果修改了 security 默认密码编码器，需同步修改这里
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleService sysRoleService;

    @Transactional
    @Override
    public void createUser(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO, sysUser);
        sysUser.setPassword(passwordEncoder.encode(sysUserDTO.getPassword()));
        sysUserMapper.insert(sysUser);
        List<Long> roleIds = sysUserDTO.getRoleIds();
        if (!CollectionUtils.isEmpty(roleIds)) {
            ArrayList<SysUserRole> userRoles = new ArrayList<>(roleIds.size());
            roleIds.forEach(item -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(item);
                userRoles.add(sysUserRole);
            });
            sysUserRoleMapper.insertBatch(userRoles);
        }
    }

    @Override
    public void deleteUsers(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new BadRequestException("请选择要删除的用户");
        }
        sysUserMapper.delete(Wrappers.<SysUser>lambdaUpdate().in(SysUser::getId, ids));
    }

    @Override
    public void updateUser(SysUserDTO sysUserDTO) {
        SysUser sysUser = sysUserMapper.selectById(sysUserDTO.getId());
        if (sysUser == null) {
            throw new BadRequestException("不存在该记录");
        }
        BeanUtils.copyProperties(sysUserDTO, sysUser, "password");
        if (StringUtils.hasText(sysUser.getPassword())) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public List<SysUserVO> queryUserList() {
        List<SysUser> sysUsers = sysUserMapper.selectList(Wrappers.lambdaQuery());
        return Optional.ofNullable(sysUsers).orElse(Collections.emptyList())
                .stream().map(item -> {
                    SysUserVO sysUserVO = new SysUserVO();
                    BeanUtils.copyProperties(item, sysUserVO);
                    return sysUserVO;
                }).collect(Collectors.toList());
    }

    @Override
    public PageResult<SysUserVO> getUserPage(SysUserPageDTO sysUserPageDTO) {
        IPage<SysUser> page = PageUtils.startPage(sysUserPageDTO);
        IPage<SysUser> roleIPage = sysUserMapper.selectPage(page, Wrappers.lambdaQuery());
        PageResult<SysUserVO> sysUserVOPageResult = PageUtils.convert(roleIPage, item -> {
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtils.copyProperties(item, sysUserVO);
            return sysUserVO;
        });
        sysUserVOPageResult.setData(fillRoleList(sysUserVOPageResult.getData()));
        return sysUserVOPageResult;
    }

    public List<SysUserVO> fillRoleList(List<SysUserVO> sysUserVOS) {
        if (CollectionUtils.isEmpty(sysUserVOS)) {
            return sysUserVOS;
        }
        List<Long> userIds = sysUserVOS.stream().map(BaseVO::getId).collect(Collectors.toList());
        // 查到与当前用户有关的所有用户角色列表
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getUserId, userIds));
        List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        // 查询角色列表
        List<SysRoleVO> roleVOList = sysRoleService.roleListByRoleIds(roleIds);
        Map<Long, SysRoleVO> roleVOMap = roleVOList.stream().collect(
                Collectors.toMap(SysRoleVO::getId, Function.identity(), (existing, replacement) -> existing));
        // 设置角色信息
        userRoles.forEach(item -> {
            SysRoleVO sysRoleVO = roleVOMap.get(item.getRoleId());
            if (sysRoleVO == null) {
                return;
            }
            item.setRoleInfo(sysRoleVO);
        });
        Map<Long, List<SysUserRole>> userRoleMap = userRoles.stream().collect(Collectors.groupingBy(SysUserRole::getUserId));
        sysUserVOS.forEach(item -> {
            item.setRoleList(userRoleMap.getOrDefault(item.getId(),
                            Collections.emptyList()).stream().map(SysUserRole::getRoleInfo)
                    .filter(Objects::nonNull).collect(Collectors.toList()));
        });
        return sysUserVOS;
    }
}
