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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author love
 * @since 2025/5/16 14:19
 */
@Tag(name = "demo")
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo")
public class DemoController {

    @PostMapping("/v1/logout")
    public ResponseData<Void> logout() {
        return ResponseCode.SUCCESS.getResponse("退出登录成功");
    }

    @Deprecated
    @Operation(summary = "测试", deprecated = true)
    @GetMapping(value = "/test")
    public String hello() {
        return "test";
    }
}
