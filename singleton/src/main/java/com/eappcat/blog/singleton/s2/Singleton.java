package com.eappcat.blog.singleton.s2;

/**
 * 饿汉式
 */
public class Singleton {
    private static Singleton singleton=new Singleton();
    private Singleton(){
        System.out.println("22222");
    }
    public static Singleton getInstance(){
        return singleton;
    }
    public static void test(){
    }
}
