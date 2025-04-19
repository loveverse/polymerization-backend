package com.loveverse.study.design.patterns.creational.factory.methodFactory;

public class WangZheFactory implements GameFactory{
    @Override
    public Game createGame() {
        return new WangZhe();
    }
}
