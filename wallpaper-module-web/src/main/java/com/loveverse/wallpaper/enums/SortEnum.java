package com.loveverse.wallpaper.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public enum SortEnum {
    HOTTEST("1", "最热","喜欢次数最多的壁纸"),
    ULTRAMODERN("2", "最新","最新创建的"),
    RANDOM("3", "随机","啥也不干");

    private final String value;

    private final String label;

    private final String desc;

}
