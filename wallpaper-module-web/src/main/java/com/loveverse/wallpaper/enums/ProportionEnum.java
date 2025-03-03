package com.loveverse.wallpaper.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Getter
@RequiredArgsConstructor
public enum ProportionEnum {
    ONE_OVER_ONE(1, "1:1"),
    FOUR_OVER_THREE(2, "4:3"),
    SIXTEEN_OVER_NINE(3, "16:9"),
    EIGHTEEN_OVER_NINE(4, "18:9"),
    TWENTY_ONE_OVER_NINE(5, "21:9"),
    EVEN_BIGGER(6, "更大");
    private final int value;
    private final String label;
}
