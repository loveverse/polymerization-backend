package com.loveverse.study.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.study.design.patterns.creational.factory.abstractFactory.AbstractFactory;
import com.loveverse.study.design.patterns.creational.factory.abstractFactory.IPhoneProduct;
import com.loveverse.study.design.patterns.creational.factory.abstractFactory.IRouterProduct;

// 小米工厂类
public class XiaoMiFactory implements AbstractFactory {
    @Override
    public IPhoneProduct phoneProduct() {
        return new XiaoMiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new XiaoMiRouter();
    }
}
