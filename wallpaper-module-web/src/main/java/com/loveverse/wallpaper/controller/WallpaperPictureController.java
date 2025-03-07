package com.loveverse.wallpaper.controller;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/wallpaper/picture")
public class WallpaperPictureController {
    private final IWallpaperPictureService wallpaperPictureService;

    @PostMapping("/list")
    public ResponseData<List<WallpaperPicture>> pictureList(@RequestBody PictureReqDto dto) {
        List<WallpaperPicture> list = wallpaperPictureService.queryList(dto);
        return ResponseCode.SUCCESS.getResponse(list);
    }
}
