package com.loveverse.study.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.study.design.patterns.creational.factory.abstractFactory.AbstractFactory;
import com.loveverse.study.design.patterns.creational.factory.abstractFactory.IPhoneProduct;
import com.loveverse.study.design.patterns.creational.factory.abstractFactory.IRouterProduct;

public class MeiZuFactory implements AbstractFactory {
    @Override
    public IRouterProduct routerProduct() {
        return new MeiZuRouter();
    }

    @Override
    public IPhoneProduct phoneProduct() {
        return new MeiZuPhone();
    }
}
