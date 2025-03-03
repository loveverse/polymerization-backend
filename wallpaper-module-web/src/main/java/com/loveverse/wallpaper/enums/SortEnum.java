package com.loveverse.wallpaper.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public enum SortEnum {
    HOTTEST("1", "最热"),
    ULTRAMODERN("2", "最新"),
    RANDOM("3", "随机");

    private final String value;

    private final String label;


}
