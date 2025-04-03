package com.loveverse.wallpaper.controller;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@RestController
@Tag(name = "壁纸用户管理")
@RequestMapping("/wallpaper/user")
public class WallpaperUserController {

    @Operation(summary = "用户列表")
    @PostMapping
    public ResponseData<Void> list() {
        return ResponseCode.SUCCESS.getResponse();
    }
}
