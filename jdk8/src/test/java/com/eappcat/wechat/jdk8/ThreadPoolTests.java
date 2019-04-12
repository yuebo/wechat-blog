package com.eappcat.wechat.jdk8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class ThreadPoolTests {
    ExecutorService executorService= Executors.newSingleThreadExecutor();
    @Test
    public void singleThreadPool() throws InterruptedException {
        LinkedList linkedList=new LinkedList();
        for (int i=0;i<100000000;i++){
            executorService.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            linkedList.add(i);
            if(i%1000==0){
                System.out.println(i+":"+Runtime.getRuntime().freeMemory()+"/"+Runtime.getRuntime().totalMemory());
            }
        }
        executorService.awaitTermination(10,TimeUnit.MINUTES);
    }
}
