package com.eappcat.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.context.support.ResourceBundleThemeSource;

import java.util.Arrays;

@Configurable
public class User {
    @Autowired
    ApplicationContext applicationContext;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return Arrays.asList(applicationContext.getBeanDefinitionNames()).toString();
    }
}
