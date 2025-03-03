package com.loveverse.wallpaper.dto;

import lombok.Data;

@Data
public class PictureReqDto {
    /**
     * 排序字段
     */
    private String sort;

    /**
     * 分辨率
     */
    private int resolutionRatio;

    /**
     * 比例
     */
    private int proportion;

    /**
     * 主色调
     */
    private int primaryColor;

}
