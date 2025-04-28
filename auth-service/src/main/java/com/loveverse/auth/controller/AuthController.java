package com.loveverse.auth.controller;

import com.loveverse.auth.dto.login.LoginInfoReq;
import com.loveverse.auth.dto.login.LoginInfoRes;
import com.loveverse.auth.service.AuthService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author love
 * @since 2025/4/23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/system")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/v1/user/register")
    public ResponseData<Void> register() {
        return null;
    }

    @PostMapping("/v1/user/login")
    public ResponseData<LoginInfoRes> login(@RequestBody LoginInfoReq loginInfoReq) {
        LoginInfoRes loginInfoRes = authService.userLogin(loginInfoReq);
        return ResponseCode.SUCCESS.getResponse(loginInfoRes);
    }

    @PostMapping("/v1/user/logout")
    public ResponseData<Void> logout() {
        return ResponseCode.SUCCESS.getResponse("退出登录成功");
    }
}
