package com.loveverse.wallpaper.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.loveverse.core.dto.PageResult;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.enums.SortEnum;
import com.loveverse.wallpaper.entity.Picture;
import com.loveverse.wallpaper.mapper.WallpaperPictureMapper;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@Service
@RequiredArgsConstructor
public class WallpaperPictureServiceImpl implements IWallpaperPictureService {
    private final WallpaperPictureMapper pictureMapper;

    @Override
    public PageResult<Picture> queryPageList(PictureReqDto qry) {
        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        String sort = qry.getSortField();

        // 排序逻辑映射表
        Map<String, BiConsumer<LambdaQueryWrapper<Picture>, Boolean>> sortStrategyMap = new HashMap<>();
        sortStrategyMap.put(SortEnum.HOTTEST.getValue(), (wrapper, desc) -> wrapper.orderByDesc(Picture::getLikeCount));
        sortStrategyMap.put(SortEnum.ULTRAMODERN.getValue(), (wrapper, desc) -> wrapper.orderByDesc(Picture::getCreateTime));

        // 根据排序类型应用策略
        if (StrUtil.isNotBlank(sort)) {
            BiConsumer<LambdaQueryWrapper<Picture>, Boolean> strategy = sortStrategyMap.get(sort);
            if (strategy != null) {
                strategy.accept(queryWrapper, true); // 默认降序
            } else {
                throw new BadRequestException("不支持的排序类型: " + sort);
            }
        }
        //Page<Picture> picturePage = pictureMapper.selectPage(qry.getIPage(), queryWrapper);
        //return PageUtils.getPage(picturePage, picturePage.getRecords());
        return null;
    }
}
