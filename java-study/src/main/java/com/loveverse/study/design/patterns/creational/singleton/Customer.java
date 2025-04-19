package com.loveverse.study.design.patterns.creational.singleton;

public class Customer {
    public static void main(String[] args) {
        SingleObject instance = SingleObject.getInstance();
        instance.showMessage();
    }
}
