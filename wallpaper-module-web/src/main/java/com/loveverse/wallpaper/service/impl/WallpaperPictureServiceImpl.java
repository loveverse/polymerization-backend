package com.loveverse.wallpaper.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.enums.SortEnum;
import com.loveverse.wallpaper.entity.Picture;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.loveverse.wallpaper.mapper.WallpaperPictureMapper;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@Service
public class WallpaperPictureServiceImpl extends ServiceImpl<WallpaperPictureMapper, WallpaperPicture> implements IWallpaperPictureService {

    @Override
    public List<WallpaperPicture> queryList(PictureReqDto dto) {
        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        String sort = dto.getSort();
        if(StrUtil.isNotBlank(sort)){
            if(SortEnum.HOTTEST.getValue().equals(sort)){
                // 最热：查询喜欢次数最多的
                queryWrapper.orderByDesc(Picture::getLikeCount);
            }else if (SortEnum.ULTRAMODERN.getValue().equals(sort)){
                // 最新：时间降序
                queryWrapper.orderByDesc(Picture::getCreateTime);
            }
        }
        return null;
    }
}
