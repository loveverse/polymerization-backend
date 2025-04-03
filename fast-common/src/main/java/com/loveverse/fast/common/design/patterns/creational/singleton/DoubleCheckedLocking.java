package com.loveverse.fast.common.design.patterns.creational.singleton;

public class DoubleCheckedLocking {
    private volatile static DoubleCheckedLocking instance;

    private DoubleCheckedLocking() {
    }
    // 双检锁/双重校验锁
    public static DoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }
}
