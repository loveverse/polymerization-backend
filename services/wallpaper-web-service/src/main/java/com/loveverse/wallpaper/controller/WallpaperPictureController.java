package com.loveverse.wallpaper.controller;

import com.loveverse.core.dto.PageResult;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.entity.Picture;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

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
    public ResponseData<PageResult<Picture>> pictureList(@Valid @RequestBody PictureReqDto query) {
        PageResult<Picture> pageList = wallpaperPictureService.queryPageList(query);
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
