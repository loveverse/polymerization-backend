package com.loveverse.auth.service.impl;

import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.mapper.SysUserMapper;
import com.loveverse.auth.request.SysUserReqDTO;
import com.loveverse.auth.response.SysUserResVO;
import com.loveverse.auth.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    @Override
    public void createUser(SysUserReqDTO sysUserReqDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserReqDTO, sysUser);
        sysUser.setPassword(passwordEncoder.encode(sysUserReqDTO.getPassword()));
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void deleteUsers(Long[] ids) {

    }

    @Override
    public void updateUser(SysUserReqDTO sysUserReqDTO) {

    }

    @Override
    public List<SysUserResVO> queryUserList() {
        return Collections.emptyList();
    }
}
