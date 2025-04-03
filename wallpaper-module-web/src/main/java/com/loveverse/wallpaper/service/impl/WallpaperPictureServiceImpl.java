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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<WallpaperPicture> queryList(PictureReqDto dto) {
        // 输入参数校验
        if (dto == null) {
            throw new IllegalArgumentException("请求参数不能为空");
        }

        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        String sort = dto.getSort();

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
                throw new IllegalArgumentException("不支持的排序类型: " + sort);
            }
        }

        try {
            // 执行查询并转换结果
            List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
            return pictureList.stream()
                    .map(this::convertToWallpaperPicture) // 假设存在转换方法
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // 异常处理
            throw new RuntimeException("查询图片列表失败", e);
        }
    }

    // 假设的转换方法
    private WallpaperPicture convertToWallpaperPicture(Picture picture) {
        WallpaperPicture wallpaperPicture = new WallpaperPicture();
        // 转换逻辑
        return wallpaperPicture;
    }

}
