package com.loveverse.auth.service;

import com.loveverse.auth.dto.login.LoginInfoReq;
import com.loveverse.auth.dto.login.LoginInfoRes;

/**
 * @author love
 * @since 2025/4/28
 */
public interface AuthService {
    LoginInfoRes userLogin(LoginInfoReq loginInfoReq);
}
