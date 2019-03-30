package com.eappcat.wechat.jdk8;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

@RunWith(JUnit4.class)
public class EqualsTests {

    @Test
    public void testEqualsInt(){
        int a1=1;
        Integer a2=new Integer("1");
        Integer a3=new Integer("1");
        Integer a4=1;
        Integer a5=1;
        Integer a6=-129;
        Integer a7=-129;
        //比较基本类型和封装类型，会转换为基本类型后比较
        Assert.assertTrue(a1==a2);
        //比较基本类型和封装类型，会转换为基本类型后比较
        Assert.assertTrue(a1==a3);
        //比较基本类型和封装类型，会转换为基本类型后比较
        Assert.assertTrue(a2==a1);
        //比较对象地址，new出来的显然是不同的
        Assert.assertFalse(a2==a3);
        //比较对象地址，这个套路比较深，java里面会的0~255里面的整数做缓存，返回相同的对象，超出范围的才new
        Assert.assertTrue(a4==a5);
        //比较对象地址，这个套路比较深，java里面会的0~255里面的整数做缓存，返回相同的对象，超出范围的才new
        Assert.assertFalse(a6==a7);
    }

    @Test
    public void testEquals(){
        Student o1=new Student(null);
        Student o2=new Student(null);
        Student o3=new Student("1111");
        Student o4=new Student("1111");
        Student o5=new Student("2222");
        Assert.assertTrue(o1.equals(o2));
        Assert.assertFalse(o1.equals(o3));
        Assert.assertFalse(o1.equals(null));
        Assert.assertTrue(o3.equals(o4));
        Assert.assertFalse(o4.equals(o5));


    }
    @Test
    public void testEqualsInArray(){
        Student o1=new Student(null);
        Student o2=new Student(null);
        Student o3=new Student("1111");
        Student o4=new Student("1111");
        Student o5=new Student("2222");
        ArrayList<Student> list=new ArrayList();
        HashSet<Student> set=new HashSet();
        list.add(o1);
        set.add(o1);
        Assert.assertTrue(list.contains(o1));
        Assert.assertTrue(list.contains(o2));
        Assert.assertTrue(set.contains(o1));
        //没有重写hashCode导致HashSet无法正确判断元素
        Assert.assertTrue(set.contains(o2));

    }
    @AllArgsConstructor
//    @EqualsAndHashCode(of = "name")
    public static class Student{
        private String name;
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Student){
                return new EqualsBuilder().append(this.name,((Student) obj).name).isEquals();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(this.name).toHashCode();
        }
    }

}
