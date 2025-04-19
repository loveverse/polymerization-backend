package com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.AbstractFactory;
import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.IPhoneProduct;
import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.IRouterProduct;

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
