package com.zyu.corejava.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by chenjie on 2016/2/19.
 */
public class CyclicBarrierTest {

    public static void main(String[] args){

        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"来扫尾");
            }
        });

        for (int i = 0; i < 4; i++) {
            new Write(barrier).start();
        }

    }
}
class  Write extends Thread{

    private CyclicBarrier cyclicBarrier;

    public Write(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run(){
        System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
        try {
            Thread.sleep((long) (Math.random()*1000));
            System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程写入完毕，继续处理其他任务...");
    }
}