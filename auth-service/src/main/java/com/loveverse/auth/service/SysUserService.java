package com.loveverse.auth.service;

import com.loveverse.auth.request.SysUserDTO;
import com.loveverse.auth.response.SysUserVO;


import java.util.List;

/**
 * @author love
 * @since 2025/5/19 18:00
 */
public interface SysUserService {
    void createUser(SysUserDTO sysUserReqDTO);

    void deleteUsers(Long[] ids);

    void updateUser(SysUserDTO sysUserReqDTO);

    List<SysUserVO> queryUserList();
}
