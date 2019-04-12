package com.eappcat.wechat.jdk8.reorder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TheadTest {
    public static void main(String ... args) throws InterruptedException {
        MyThread myThread=new MyThread();
        myThread.setDaemon(false);
        myThread.start();

        Thread.sleep(100);
        myThread.setRunning(false);
        System.out.println("set running to false");



    }
    public static class MyThread extends Thread{
        private volatile boolean running=true;

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            while (running){
                System.out.println("I am running");
            }
            System.out.println("I am stopping");

        }
    }
}
