package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.entity.SysUserRole;
import com.loveverse.auth.mapper.SysUserMapper;
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
    private final SysRoleService sysRoleService;
    private final SysUserRoleServiceImpl sysUserRoleService;

    @Transactional
    @Override
    public void createUser(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO, sysUser);
        sysUser.setPassword(passwordEncoder.encode(sysUserDTO.getPassword()));
        sysUserMapper.insert(sysUser);
        List<Long> roleIds = sysUserDTO.getRoleIds();
        saveUserRoles(roleIds, sysUser.getId());
    }

    @Transactional
    @Override
    public void deleteUsers(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new BadRequestException("请选择要删除的用户");
        }
        sysUserMapper.delete(Wrappers.<SysUser>lambdaUpdate().in(SysUser::getId, ids));
        sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getUserId, ids));
    }

    @Transactional
    @Override
    public void updateUser(SysUserDTO sysUserDTO) {
        SysUser sysUser = sysUserMapper.selectById(sysUserDTO.getId());
        if (sysUser == null) {
            throw new BadRequestException("不存在该记录");
        }
        BeanUtils.copyProperties(sysUserDTO, sysUser, "password");
        // 密码传入空字符数据库不会更新
        if (StringUtils.hasText(sysUser.getPassword())) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        sysUserMapper.updateById(sysUser);
        // 删除该用户的所有旧角色关联
        sysUserRoleService.remove(
                Wrappers.<SysUserRole>lambdaQuery()
                        .eq(SysUserRole::getUserId, sysUser.getId())
        );
        saveUserRoles(sysUserDTO.getRoleIds(), sysUser.getId());
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
        List<SysUserRole> userRoles = sysUserRoleService.getUserRoles(userIds);
        if (CollectionUtils.isEmpty(userRoles)) {
            sysUserVOS.forEach(item -> item.setRoleList(Collections.emptyList()));
            return sysUserVOS;
        }

        List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).distinct().collect(Collectors.toList());
        List<SysRoleVO> roleVOList = sysRoleService.roleListByRoleIds(roleIds);
        Map<Long, SysRoleVO> roleVOMap = roleVOList.stream().collect(
                Collectors.toMap(SysRoleVO::getId, Function.identity()));

        Map<Long, List<SysUserRole>> userRoleMap = userRoles.stream().collect(Collectors.groupingBy(SysUserRole::getUserId));
        sysUserVOS.forEach(item -> {
            List<SysRoleVO> roles = userRoleMap.getOrDefault(item.getId(), Collections.emptyList())
                    .stream().map(userRole -> roleVOMap.get(userRole.getRoleId()))
                    .filter(Objects::nonNull).collect(Collectors.toList());
            item.setRoleList(roles);
        });
        return sysUserVOS;
    }

    private void saveUserRoles(List<Long> roleIds, Long userId) {
        // 空列表直接返回
        if (!CollectionUtils.isEmpty(roleIds)) {
            // 创建用户角色关联对象列表
            List<SysUserRole> userRoles = roleIds.stream().map(item -> {
                        SysUserRole sysUserRole = new SysUserRole();
                        //sysUserRole.setId(idGenerator.generateId());
                        sysUserRole.setUserId(userId);
                        sysUserRole.setRoleId(item);
                        //sysUserRole.setCreateTime(LocalDateTime.now());
                        //sysUserRole.setUpdateTime(LocalDateTime.now());
                        //sysUserRole.setValid(1);
                        //sysUserRole.setVersion(0L);
                        return sysUserRole;
                    })
                    .collect(Collectors.toList());

            // 批量保存
            sysUserRoleService.saveBatch(userRoles);
        }
    }
}
