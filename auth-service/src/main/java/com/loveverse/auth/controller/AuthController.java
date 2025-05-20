package com.loveverse.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.loveverse.auth.constant.RedisKeyConstant;
import com.loveverse.auth.request.UserLoginDTO;
import com.loveverse.auth.response.UserLoginVO;
import com.loveverse.auth.service.AuthService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.redis.util.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@ApiSupport(order = 99)
@RequiredArgsConstructor
@RestController // 自动将响应转换为 json 格式
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
    public ResponseData<UserLoginVO> login(@RequestBody @Valid UserLoginDTO loginInfoReq) {
        UserLoginVO loginInfoRes = authService.userLogin(loginInfoReq);
        return ResponseCode.SUCCESS.getResponse(loginInfoRes);
    }

    @Operation(summary = "获取验证码", description = "返回 jpeg 格式, 直接拼接地址即可", tags = "验证码重新分组", parameters = {
            @Parameter(name = "uuid", description = "唯一标识符，用于绑定验证码校验", required = true, example = "abc123xyz"),
            @Parameter(name = "w", description = "验证码图片宽度，默认值为 200", example = "200", schema = @Schema(type = "int")),
            @Parameter(name = "h", description = "验证码图片高度，默认值为 100", example = "100")
    }, responses = {@ApiResponse(
            responseCode = "200",
            description = "验证码图片",
            content = @Content(
                    mediaType = "image/jpeg",
                    schema = @Schema(type = "string", format = "binary")
            )
    )})
    @GetMapping(value = "/v1/captcha/{uuid}", produces = "image/jpeg") // Todo 指定 produces 没有效果，接口未作校验，可能导致频繁刷新验证码
    public void captcha(HttpServletResponse response,
                        @PathVariable("uuid") String uuid,
                        @RequestParam(value = "w", defaultValue = "200") int w,
                        @RequestParam(value = "h", defaultValue = "100") int h) throws IOException {

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(w, h, 4, 50);
        String key = RedisKeyConstant.build(RedisKeyConstant.CAPTCHA_UUID, uuid);
        redisUtils.set(key, lineCaptcha.getCode(), 60);
        // 输出图片流
        lineCaptcha.write(response.getOutputStream());
    }

    @PostMapping("/v1/logout")
    public ResponseData<Void> logout() {
        return ResponseCode.SUCCESS.getResponse("退出登录成功");
    }

    @Deprecated
    @Operation(summary = "测试", deprecated = true)
    @GetMapping(value = "/test", produces = "image/jpeg")   // Todo 没有参数指定在文档有效果
    public ResponseData<String> hello() {
        return ResponseCode.SUCCESS.getResponse("测试指定返回类型", "hello");
    }
}
