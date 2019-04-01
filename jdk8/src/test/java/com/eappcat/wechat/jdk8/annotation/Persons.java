package com.eappcat.wechat.jdk8.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
public @interface Persons {
    Person[] value();
}
