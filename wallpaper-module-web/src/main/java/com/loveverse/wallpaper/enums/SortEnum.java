package com.loveverse.wallpaper.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public enum SortEnum {
    HOTTEST("1", "最热","最热的壁纸"),
    ULTRAMODERN("2", "最新"),
    RANDOM("3", "随机");

    private final String value;

    private final String label;

    private final String desc;

    SortEnum(String value, String label) {
        this(value, label, label);
    }
    public String getDescription() {
        return StringUtils.hasText(desc) ? desc : label;
    }


    public static String getDescriptionText() {
        StringBuilder builder = new StringBuilder();
        for (SortEnum value : SortEnum.values()) {
            builder.append(value.value).append(" - ").append(value.label).append("; ");
        }
        return builder.toString();
    }
}
