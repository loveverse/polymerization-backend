package com.loveverse.fast.common.design.patterns.creational.singleton;

public class SingleObject {
    // 饿汉式，类加载时就初始化，浪费内存
    private static SingleObject instance = new SingleObject();

    // 构造函数为private， 类就无法实例化
    private SingleObject() {
    }

    // 线程不安全，多线程下可能会创建多个实例
    //public static SingleObject getInstance() {
    //    // 单例模式最简单实现
    //    if (instance == null) {
    //        instance = new SingleObject();
    //    }
    //    return instance;
    //}

    // 懒汉式，线程安全，但是每次实例时都需要加锁，性能较差
    public static synchronized SingleObject getInstance(){
        if(instance == null){
            instance = new SingleObject();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World");
    }
}
