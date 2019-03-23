package com.eappcat.wechat.jdk8.intf;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface Jdk8Interface {
    int start = 1;
    String call(Consumer<String> consumer);
//    void run();
    default void call(Supplier<String> supplier){
        String clazz=this.call(Object::toString);
        System.out.println(supplier.get());
    }
    static String call(Function<String,String> function,String param){
        return function.apply(param);
    }
//    default boolean equals(Object o){
//        return true;
//    }

}
