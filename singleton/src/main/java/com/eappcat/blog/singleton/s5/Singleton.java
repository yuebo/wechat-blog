package com.eappcat.blog.singleton.s5;

/**
 * 静态内部类
 */
public class Singleton {
    private static class SingleHolder{
        private final static Singleton instance=new Singleton();
    }
    private Singleton(){
    }
    public static Singleton getInstance(){
        return SingleHolder.instance;
    }
}
