package com.loveverse.wallpaper.service;

import com.loveverse.core.dto.PageResult;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.entity.Picture;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
public interface IWallpaperPictureService {


    PageResult<Picture> queryPageList(PictureReqDto query);
}
