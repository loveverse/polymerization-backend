package com.loveverse.fast.common.design.patterns.creational.builder;

public class NightBuilder implements Builder {
    private final Life life = new Life();

    @Override
    public void buildEat() {
        life.setEat("吃饭");
    }

    @Override
    public void buildPlay() {
        life.setPlay("玩王者");
    }

    @Override
    public void buildRest() {
        life.setRest("休息8小时");
    }

    @Override
    public Life getResult() {
        return life;
    }
}
