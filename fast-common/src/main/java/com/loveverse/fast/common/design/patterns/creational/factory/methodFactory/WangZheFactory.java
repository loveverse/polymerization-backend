package com.loveverse.fast.common.design.patterns.creational.factory.methodFactory;

public class WangZheFactory implements GameFactory{
    @Override
    public Game createGame() {
        return new WangZhe();
    }
}
