package com.zyu.corejava.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by chenjie on 2016/2/19.
 */
public class CountDownLatchTest {

    public static void main(String[] args){

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            new Thread(){
                public void run(){
                    try {
                        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                        Thread.sleep(3000);
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


        try {
            System.out.println("等待2个子线程执行完毕...");
            countDownLatch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
}
