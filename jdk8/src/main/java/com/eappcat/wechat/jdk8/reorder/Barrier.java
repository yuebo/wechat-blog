package com.eappcat.wechat.jdk8.reorder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Barrier {
    int a = 0;
    int b = 0;
    int x = 0;
    int y = 0;
    private static ExecutorService executorService=Executors.newSingleThreadExecutor();
    private static ExecutorService executorService1=Executors.newSingleThreadExecutor();
    private static ExecutorService executorService2=Executors.newSingleThreadExecutor();

    public static void main(String ... args) throws InterruptedException {
        for (int i=0;i< 1000000;i++){
            //初始化：
            Barrier barrier=new Barrier();
            //处理器A执行
            executorService1.submit(()->{
                barrier.a = 1; //A1
                barrier.x = barrier.b; //A2
                print(barrier);
            });

            //处理器B执行
            executorService2.submit(()->{
                barrier.b = 2; //B1
                barrier.y = barrier.a; //B2
                print(barrier);

            });



        }

    }
    public static void print(Barrier barrier){
        executorService.submit(()->{
            if(barrier.x==0&&barrier.y==0){
                System.out.println(String.format("=======>%s,%s,%s,%s",barrier.a,barrier.b,barrier.x,barrier.y));

            }else {
                System.out.println(String.format("%s,%s,%s,%s",barrier.a,barrier.b,barrier.x,barrier.y));
            }
        });
    }
}
