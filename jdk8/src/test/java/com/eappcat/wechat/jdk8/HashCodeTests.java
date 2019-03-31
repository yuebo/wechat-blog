package com.eappcat.wechat.jdk8;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;

@RunWith(JUnit4.class)
public class HashCodeTests {
    @Test
    public void testHashCodeMode2(){
        //test XX:hashCode=2,always returns 1
        String s=new String("111");
        Assert.assertTrue(s.hashCode()!=1);
        Assert.assertTrue(System.identityHashCode(s)==1);
    }

    @Test
    public void testHashCodeMode3(){
        Student student1=new Student("123");
        Student student2=new Student("123");
        HashSet<Student> students=new HashSet<>();
        students.add(student1);
        students.add(student2);
        System.out.println("student1 equals student2: "+student1.equals(student2));
        System.out.println("student1 hashCode == student2 hashCode : "+(student1.hashCode()==student2.hashCode()));
        System.out.println(students);
    }

    @AllArgsConstructor
    public static class Student{
        private String name;
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Student){
                Student student=(Student) obj;
                return new EqualsBuilder().append(this.name, student.name).isEquals();
            }
            return false;
        }

        @Override
        public String toString() {
            return this.name;
        }
        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(this.name).toHashCode();
        }
    }
}
