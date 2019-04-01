package com.eappcat.wechat.jdk8.annotation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;

@RunWith(JUnit4.class)
public class AnnotationTests {

    @Test
    public void testParent(){
        Person person=Parent.class.getAnnotation(Person.class);
        Assert.assertEquals(person.name(),"parent");
    }
    @Test
    public void testChild(){
        //继承父类上的注解
        Person person=Child.class.getAnnotation(Person.class);
        Assert.assertEquals(person.name(),"parent");
    }

    @Test
    public void testChild2(){
        //不能继承接口上的注解
        Person person=Child2.class.getAnnotation(Person.class);
        Assert.assertNull(person);
    }

    @Test
    public void testParentMethod() throws NoSuchMethodException {
        //不能继承接口上的注解
        Method method=Parent.class.getMethod("eat",null);
        Person person=method.getAnnotation(Person.class);
        Assert.assertEquals(person.name(),"parent.eat");
    }
    @Test
    public void testChildMethod() throws NoSuchMethodException {
        //方法上的注解不管是否有@Inherited都能继承
        Method method=Child.class.getMethod("eat",null);
        Person person=method.getAnnotation(Person.class);
        Assert.assertEquals(person.name(),"parent.eat");
    }


    @Test
    public void testChild3(){
        //获取相同的注解
        Person[] person=Child3.class.getAnnotationsByType(Person.class);
        Assert.assertEquals(person.length,2);
        //获取相同的注解
        Persons persons=Child3.class.getAnnotation(Persons.class);
        Assert.assertEquals(persons.value().length,2);
    }

}
