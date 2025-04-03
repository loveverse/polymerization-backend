package com.loveverse.fast.common.design.patterns.creational.factory.methodFactory;

public class Customer {
    public static void main(String[] args) {
        /**
         * 工厂方法创建类
         * 优点：符合开闭原则，增加新的工厂类，无需修改现有代码
         * 缺点：每增加一个产品，需要增加一个工厂类，增加系统复杂度
         */
        NaiKuaiFactory naiKuaiFactory = new NaiKuaiFactory();
        Game game = naiKuaiFactory.createGame();
        game.getGameInfo();

        WangZheFactory wangZheFactory = new WangZheFactory();
        Game game1 = wangZheFactory.createGame();
        game1.getGameInfo();
    }
}
