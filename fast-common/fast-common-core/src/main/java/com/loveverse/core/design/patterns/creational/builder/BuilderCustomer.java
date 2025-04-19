package com.loveverse.fast.common.design.patterns.creational.builder;

public class BuilderCustomer {
    public static void main(String[] args) {
        // 早上的生活
        Builder amBuilder = new AmBuilder();
        Director director = new Director(amBuilder);
        director.construct();
        Life result = amBuilder.getResult();
        System.out.println(result);

        // 中午的生活
        Builder pmBuilder = new PmBuilder();
        Director pmDirector = new Director(pmBuilder);
        pmDirector.construct();
        Life result1 = pmBuilder.getResult();
        System.out.println(result1);

        /**
         * 建造者模式（生成器模式）
         * 优点：通过逐步构建复杂对象来分离对象的构造过程与表示，隔离复杂对象的构建和使用
         * 缺点：相比其他创建型模式，运行时效率较低
         */
    }
}
