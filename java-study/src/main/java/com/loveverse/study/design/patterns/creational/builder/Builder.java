package com.loveverse.study.design.patterns.creational.builder;

public interface Builder {
    void buildEat();

    void buildPlay();

    void buildRest();

    Life getResult();
}
