package com.loveverse.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.loveverse.auth.dto.login.LoginInfoReq;
import com.loveverse.auth.dto.login.LoginInfoRes;
import com.loveverse.auth.service.AuthService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.redis.util.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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
    private final RedisUtils redisUtils;

    @PostMapping("/v1/register")
    public ResponseData<Void> register() {
        return null;
    }

    @Operation(summary = "系统用户登录", description = "需要使用 Base64 编码,兼容传输明文密码")
    @PostMapping("/v1/login")
    public ResponseData<LoginInfoRes> login(@Valid @RequestBody LoginInfoReq loginInfoReq) {
        LoginInfoRes loginInfoRes = authService.userLogin(loginInfoReq);
        return ResponseCode.SUCCESS.getResponse(loginInfoRes);
    }

    @GetMapping("/v1/captcha/{uuid}")
    public void captcha(HttpServletResponse response,
                        @PathVariable("uuid") String uuid,
                        @RequestParam(value = "w", defaultValue = "200") int w,
                        @RequestParam(value = "h", defaultValue = "100") int h) throws IOException {

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(w, h,4,50);

        redisUtils.set("captcha_" + uuid, lineCaptcha.getCode(), 5 * 60 * 1000);
        // 输出图片流
        lineCaptcha.write(response.getOutputStream());
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
