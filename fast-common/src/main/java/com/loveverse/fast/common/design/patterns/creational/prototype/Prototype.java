package com.loveverse.fast.common.design.patterns.creational.prototype;

public interface Prototype extends Cloneable {
    Prototype clone() throws CloneNotSupportedException;
}
