package com.loveverse.fast.common.design.patterns.creational.factory.simpleFactory;

public class GameFactory {
    public static Game createGame(String gameName) {
        if ("王者".equals(gameName)) {
            return new WangZhe();
        } else {
            return new NaiKuai();
        }
    }
}
