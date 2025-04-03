package com.loveverse.fast.common.design.patterns.creational.builder;

public class PmBuilder implements Builder {
    private final Life life = new Life();

    @Override
    public void buildEat() {
        life.setEat("吃面条");
    }

    @Override
    public void buildPlay() {
        life.setPlay("打篮球");
    }

    @Override
    public void buildRest() {
        life.setRest("休息2小时");
    }

    @Override
    public Life getResult() {
        return life;
    }
}
