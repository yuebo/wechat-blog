package com.eappcat.wechat.jdk8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class ConcurrentTests {
    @Test
    public void testCountDownLatch() throws Exception{
        CountDownLatch countDownLatch=new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
           //模拟3个线程
           Thread thread= new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getId()+" 完成了任务");
                    countDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
           thread.start();
        }
        //主线程等待3个线程完成
        countDownLatch.await();

        System.out.println("主线程完成了任务");

    }

    @Test
    public void testCyclicBarrier() throws Exception{
        CyclicBarrier cyclicBarrier=new CyclicBarrier(4);
        for (int i = 0; i < 3; i++) {
            //模拟3个线程
            Thread thread= new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getId()+" 完成了任务");
                    //设置线程处于Barrier状态
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        //将主线程处于Barrier状态，此时如果Barrier状态数量等于4，则执行下面代码
        cyclicBarrier.await();
        System.out.println("主线程完成了任务");

    }
}
