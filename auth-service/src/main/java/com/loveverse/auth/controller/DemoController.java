package com.loveverse.auth.controller;

import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
