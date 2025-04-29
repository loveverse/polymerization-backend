package com.loveverse.wallpaper.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResolutionRatioEnum {
    P1(1, "1080P"),
    P2(2, "2K"),
    P4(3, "4K"),
    P8(4, "8K");
    private final int value;

    private final String label;
}
