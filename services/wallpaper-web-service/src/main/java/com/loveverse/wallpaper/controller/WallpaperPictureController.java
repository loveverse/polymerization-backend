package com.loveverse.wallpaper.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import com.loveverse.core.dto.PageResDto;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.wallpaper.config.UserConfig;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.entity.Picture;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "图片管理", description = "图片相关接口")
@RequestMapping("/wallpaper/picture")
public class WallpaperPictureController {
    private final IWallpaperPictureService wallpaperPictureService;

    //private final UserConfig userConfig;

    @Operation(summary = "图片列表")
    @PostMapping("/list")
    public ResponseData<PageResDto<Picture>> pictureList(@Valid @RequestBody PictureReqDto query) {
        PageResDto<Picture> pageList = wallpaperPictureService.queryPageList(query);
        return ResponseCode.SUCCESS.getResponse(pageList);
    }

    @Operation(summary = "测试网关")
    @GetMapping("/hello")
    public ResponseData<String> hello() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        //stringStringHashMap.put("name", userConfig.getName());
        //stringStringHashMap.put("password", userConfig.getPassword());
        String string = stringStringHashMap.toString();
        return ResponseCode.SUCCESS.getResponse(string);
    }
}
