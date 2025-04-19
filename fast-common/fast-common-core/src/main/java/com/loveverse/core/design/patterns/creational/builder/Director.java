package com.loveverse.fast.common.design.patterns.creational.builder;

public class Director {
    public Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildEat();
        builder.buildPlay();
        builder.buildRest();
    }
}
