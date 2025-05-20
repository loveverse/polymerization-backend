package com.loveverse.auth.service;

import com.loveverse.auth.request.SysUserReqDTO;
import com.loveverse.auth.response.SysUserResVO;

import java.util.List;

/**
 * @author love
 * @since 2025/5/19 18:00
 */
public interface SysUserService {
    void createUser(SysUserReqDTO sysUserReqDTO);

    void deleteUsers(Long[] ids);

    void updateUser(SysUserReqDTO sysUserReqDTO);

    List<SysUserResVO> queryUserList();
}
