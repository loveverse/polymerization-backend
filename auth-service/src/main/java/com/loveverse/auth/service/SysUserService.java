package com.loveverse.auth.service;

import com.loveverse.auth.request.SysUserDTO;
import com.loveverse.auth.request.SysUserPageDTO;
import com.loveverse.auth.response.SysUserVO;
import com.loveverse.auth.response.UserAuthorityInfoVO;
import com.loveverse.core.dto.PageResult;


import java.util.List;

/**
 * @author love
 * @since 2025/5/19 18:00
 */
public interface SysUserService {
    void createUser(SysUserDTO sysUserReqDTO);

    void deleteUsers(List<Long> ids);

    void updateUser(SysUserDTO sysUserReqDTO);

    List<SysUserVO> queryUserList();

    PageResult<SysUserVO> getUserPage(SysUserPageDTO sysUserPageDTO);

    SysUserVO getUserInfo(Long id);

    UserAuthorityInfoVO getUserAuthorityInfo(Long id);
}
