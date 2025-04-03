package com.loveverse.fast.common.design.patterns.creational.prototype;

public class ConcretePrototype implements Prototype {
    private String field;

    public ConcretePrototype(String field) {
        this.field = field;
    }

    @Override
    public Prototype clone() throws CloneNotSupportedException {
        return (ConcretePrototype) super.clone();
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
