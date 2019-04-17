package com.eappcat.wechat.jdk8;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.*;

@RunWith(JUnit4.class)
public class FunctionTests {

    @Test
    public void function1() {
        //function表示具有返回值的，具有一个参数的方法
        Function<String,Integer> function1=Integer::valueOf;
        Function function2=String::valueOf;
        //可以用lambda表达式
        Function<Object,String> function3=(a)->a.toString();
        //报错，因为无返回值
        //Function out=System.out::println;
        //Consumer可以用来表示只有一个参数，无返回值的方法
        Consumer consumer=System.out::println;
        consumer.accept("123");
        //可以用lambda表达式
        Consumer consumer2=(a)->{};


        //返回值是Boolean的function
        String o="123";
        Function<String,Boolean> function4=o::equals;


        //也可以用Predicate来表示
        Predicate<String> predicate=o::equals;


        //无参数有返回
        Supplier<Integer> supplier=o::hashCode;
        IntSupplier intSupplier=o::hashCode;
        Supplier supplier2=()-> "test";

        //二个参数的函数
        BiFunction<String,String, Boolean> test= (str,str2)->true;
        BiConsumer<String,String> test2=(str,str2)->{};
        BiPredicate<String,String> test3=(str,str2)->true;


        boolean equals=function4.apply("123");
        Assert.assertTrue(equals);
        Assert.assertTrue(callFunction(function4));
        Assert.assertTrue(predicate.test("123"));


    }

    public static Boolean callFunction(Function<String,Boolean> function){
        return function.apply("123");
    }

}
