package com.eappcat.blog.singleton;

import com.eappcat.blog.singleton.s2.Singleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestSingleton {
    @Test
    public void singleton2() throws Exception{
        System.out.println("Sleeping for 2 seconds...");
        Thread.sleep(2000);
        getClass().getClassLoader().loadClass("com.eappcat.blog.singleton.s2.Singleton");
        System.out.println("Accessing class...");
        System.out.println(Singleton.class);
        System.out.println("Sleeping for 2 seconds...");
        Thread.sleep(2000);
        System.out.println("Accessing getInstance()...");
        System.out.println(Singleton.getInstance());
    }
}
