package com.loveverse.study.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.study.design.patterns.creational.factory.abstractFactory.IRouterProduct;

public class XiaoMiRouter implements IRouterProduct {
    @Override
    public void stat() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void openwifi() {
        System.out.println("小米路由器打开wifi");
    }
}
