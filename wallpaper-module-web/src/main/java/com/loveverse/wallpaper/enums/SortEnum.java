package com.loveverse.wallpaper.enums;

import lombok.Getter;

@Getter
public enum SortEnum {
    HOTTEST(1, "最热"), ULTRAMODERN(2, "最新"), RANDOM(3, "随机");

    private final int value;

    private final String label;

    SortEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
