package com.loveverse.wallpaper.controller;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@Tag(name = "图片管理",description = "图片相关接口")
@RequestMapping("/wallpaper/picture")
public class WallpaperPictureController {
    private final IWallpaperPictureService wallpaperPictureService;

    @Operation(summary = "图片列表")
    @PostMapping("/list")
    public ResponseData<List<WallpaperPicture>> pictureList(@Valid @RequestBody PictureReqDto dto) {
        List<WallpaperPicture> list = wallpaperPictureService.queryList(dto);
        return ResponseCode.SUCCESS.getResponse(list);
    }
}
