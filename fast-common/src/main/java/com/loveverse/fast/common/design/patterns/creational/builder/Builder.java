package com.loveverse.fast.common.design.patterns.creational.builder;

public interface Builder {
    void buildEat();

    void buildPlay();

    void buildRest();

    Life getResult();
}
