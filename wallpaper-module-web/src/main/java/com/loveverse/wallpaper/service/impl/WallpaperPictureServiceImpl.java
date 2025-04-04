package com.loveverse.wallpaper.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.loveverse.fast.common.dto.PageReqDto;
import com.loveverse.fast.common.dto.PageResDto;
import com.loveverse.fast.common.exception.BadRequestException;
import com.loveverse.fast.common.util.PageUtils;
import com.loveverse.wallpaper.dto.PictureReqDto;
import com.loveverse.wallpaper.enums.SortEnum;
import com.loveverse.wallpaper.entity.Picture;
import com.loveverse.wallpaper.vo.WallpaperPicture;
import com.loveverse.wallpaper.mapper.WallpaperPictureMapper;
import com.loveverse.wallpaper.service.IWallpaperPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
    public PageResDto<Picture> queryPageList(PictureReqDto qry) {
        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        String sort = qry.getSort();

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
        Page<Picture> picturePage = pictureMapper.selectPage(qry.getIPage(), queryWrapper);
        return PageUtils.getPage(picturePage, picturePage.getRecords());
    }
}
