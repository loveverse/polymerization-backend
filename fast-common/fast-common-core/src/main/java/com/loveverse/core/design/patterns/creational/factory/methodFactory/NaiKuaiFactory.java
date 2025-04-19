package com.loveverse.fast.common.design.patterns.creational.factory.methodFactory;



public class NaiKuaiFactory implements GameFactory {
    @Override
    public Game createGame() {
        return new NaiKuai();
    }
}
