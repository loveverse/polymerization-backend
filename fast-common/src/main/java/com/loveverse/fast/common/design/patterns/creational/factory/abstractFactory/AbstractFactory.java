package com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory;

// 抽象工厂接口
public interface AbstractFactory {
    IPhoneProduct phoneProduct(); // 产品族，手机

    IRouterProduct routerProduct();
}
