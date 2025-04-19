package com.loveverse.study.design.patterns.creational.builder;

public class AmBuilder implements Builder {
    private final Life life = new Life();

    @Override
    public void buildEat() {
        life.setEat("包子");
    }

    @Override
    public void buildPlay() {
        life.setPlay("奶块");
    }

    @Override
    public void buildRest() {
        life.setRest("休息5分钟");
    }

    @Override
    public Life getResult() {
        return life;
    }
}
