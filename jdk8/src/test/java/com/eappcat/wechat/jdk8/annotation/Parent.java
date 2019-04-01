package com.eappcat.wechat.jdk8.annotation;

@Person(name = "parent")
public class Parent implements Eatable {

    @Override
    @Person(name = "parent.eat")
    public void eat() {

    }
}
