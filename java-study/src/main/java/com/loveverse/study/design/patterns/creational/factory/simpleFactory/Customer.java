package com.loveverse.study.design.patterns.creational.factory.simpleFactory;

public class Customer {
    public static void main(String[] args) {
        /**
         *
         * 简单工厂模式
         * 优点：无需知道产品得类名，只需要知道产品对应的参数，实现了创建与使用分离
         * 缺点：工厂类出现问题，整个系统会受到影响；扩展困难，违反开闭原则
         */
        Game game = GameFactory.createGame("王者");
        game.getGameInfo();
    }
}
