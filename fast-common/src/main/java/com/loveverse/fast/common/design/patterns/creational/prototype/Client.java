package com.loveverse.fast.common.design.patterns.creational.prototype;

public class Client {
    public static void main(String[] args) {
        try {
            ConcretePrototype origin = new ConcretePrototype("value");
            ConcretePrototype clone = (ConcretePrototype) origin.clone();
            /**
             * 原型模式
             * 优点：通过克隆对象，可以避免重复的初始化过程
             * 缺点：对于简单的对象创建，会增加代码的复杂性
             */

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
