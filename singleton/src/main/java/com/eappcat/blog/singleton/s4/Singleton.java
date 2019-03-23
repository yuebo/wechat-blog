package com.eappcat.blog.singleton.s4;

/**
 * 懒汉式(双重锁校验)
 */
public class Singleton {
    private static volatile Singleton singleton;
    private Singleton(){
    }
    public static Singleton getInstance(){
        if(null == singleton){
            synchronized (Singleton.class){
                if (null == singleton){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
