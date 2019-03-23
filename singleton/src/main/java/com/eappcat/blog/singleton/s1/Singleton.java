package com.eappcat.blog.singleton.s1;

/**
 * 线程不安全的单例
 */
public class Singleton {
    /**
     * 静态成员变量保存实例值
     */
    private static Singleton singleton;
    /**
     * 构造函数私有避免被其他类实例化
     */
    private Singleton(){

    }
    /**
     * 静态函数返回实例
     * @return Singleton
     */
    public static Singleton getInstance(){
        if(null == singleton){
            singleton = new Singleton();
        }
        return singleton;
    }
}
