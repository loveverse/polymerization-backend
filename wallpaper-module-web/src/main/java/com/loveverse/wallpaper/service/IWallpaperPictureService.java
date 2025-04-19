package com.loveverse.wallpaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import com.loveverse.core.dto.PageResDto;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.entity.Picture;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
public interface IWallpaperPictureService {


    PageResDto<Picture> queryPageList(PictureReqDto query);
}
