package com.eappcat.blog.singleton.s6;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态内部类
 */
public enum  Singleton {
    INSTANCE;
    Singleton(){
        System.out.println("6666");
    }
}
