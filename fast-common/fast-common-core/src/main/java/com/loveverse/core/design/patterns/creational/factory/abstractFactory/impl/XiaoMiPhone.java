package com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.IPhoneProduct;

public class XiaoMiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("小米手机开机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
