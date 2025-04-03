package com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory;

import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl.MeiZuFactory;
import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl.XiaoMiFactory;
import com.loveverse.fast.common.design.patterns.creational.factory.abstractFactory.impl.XiaoMiPhone;

@SuppressWarnings("SpellCheckingInspection")
public class Customer {
    public static void main(String[] args) {
        // 小米产品
        AbstractFactory xiaoMiFactory = new XiaoMiFactory();
        IPhoneProduct iPhoneProduct = xiaoMiFactory.phoneProduct();
        iPhoneProduct.call();
        iPhoneProduct.start();

        IRouterProduct iRouterProduct = xiaoMiFactory.routerProduct();
        iRouterProduct.stat();
        iRouterProduct.openwifi();

        // 魅族产品
        MeiZuFactory meiZuFactory = new MeiZuFactory();
        IPhoneProduct iPhoneProduct1 = meiZuFactory.phoneProduct();
        iPhoneProduct1.call();
        iPhoneProduct1.start();

        IRouterProduct iRouterProduct1 = meiZuFactory.routerProduct();
        iRouterProduct1.stat();
        iRouterProduct1.openwifi();
        /**
         * 抽象工厂
         * 产品族：一个品牌下的所有产品（每个产品下都有手机，路由器）
         * 产品等级：多个品牌下的同种产品 （小米，魅族，华为）
         * 优点：符合开闭原则，扩展产品等级比较简单，只需要实现原有的路由器，手机等
         * 缺点：产品族扩展非常困难，比如新增一个电脑，那么需要每个产品（小米，华为），都需要实现
         */
    }
}
