package com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl;

import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.IPhoneProduct;

public class MeiZuPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("魅族手机开机");
    }

    @Override
    public void call() {
        System.out.println("魅族手机打电话");
    }
}
