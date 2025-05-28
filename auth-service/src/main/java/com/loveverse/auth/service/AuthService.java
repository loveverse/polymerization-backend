package com.loveverse.auth.service;

import com.loveverse.auth.request.UserLoginDTO;
import com.loveverse.auth.response.UserLoginVO;

/**
 * @author love
 * @since 2025/4/28
 */
public interface AuthService {

    UserLoginVO userLogin(UserLoginDTO loginInfoReq);

    void userLogout();
}
