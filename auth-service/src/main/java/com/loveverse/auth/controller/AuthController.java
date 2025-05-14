package com.loveverse.auth.controller;

import com.loveverse.auth.dto.login.LoginInfoReq;
import com.loveverse.auth.dto.login.LoginInfoRes;
import com.loveverse.auth.service.AuthService;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author love
 * @since 2025/4/23
 */
@Tag(name = "登录相关")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/v1/register")
    public ResponseData<Void> register() {
        return null;
    }

    @Operation(summary = "系统用户登录", description = "需要使用 Base64 编码,兼容传输明文密码")
    @PostMapping("/v1/login")
    public ResponseData<LoginInfoRes> login(@Valid @RequestBody LoginInfoReq loginInfoReq) {
    //throw new BadRequestException("内存溢出");
        LoginInfoRes loginInfoRes = authService.userLogin(loginInfoReq);
        return ResponseCode.SUCCESS.getResponse(loginInfoRes);
    }

    @PostMapping("/v1/logout")
    public ResponseData<Void> logout() {
        return ResponseCode.SUCCESS.getResponse("退出登录成功");
    }

    @GetMapping("/test")
    public String hello() {
        return "test";
    }
}
