package com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.IRouterProduct;

public class MeiZuRouter implements IRouterProduct {
    @Override
    public void openwifi() {
        System.out.println("魅族路由器打开wifi");
    }

    @Override
    public void stat() {
        System.out.println("魅族路由器开机");
    }
}
