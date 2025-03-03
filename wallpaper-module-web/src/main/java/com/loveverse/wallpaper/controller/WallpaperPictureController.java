package com.loveverse.wallpaper.controller;

import com.loveverse.fast.common.util.BaseResponse;
import com.loveverse.fast.common.util.ResultUtils;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public BaseResponse<List<WallpaperPicture>> pictureList(@RequestBody PictureReqDto dto){
        List<WallpaperPicture> list =  wallpaperPictureService.queryList(dto);
        return ResultUtils.success(list);
    }
}
