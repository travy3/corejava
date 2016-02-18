package com.zyu.corejava.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by chenjie on 2016/2/15.
 */
public class ThreadTestDemo5 {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args){

         final ThreadTestDemo5 threadTestDemo5 = new ThreadTestDemo5();

        new Thread(new Runnable() {
            public void run() {
                threadTestDemo5.gett(Thread.currentThread());
            }
        }).start();

        new Thread(){
            public void run(){
                threadTestDemo5.gett(Thread.currentThread());
            }
        }.start();
    }
    public synchronized void get(Thread thread){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <=1){
            System.out.println(thread.getName()+"doing");
        }
        System.out.println(thread.getName()+"done");
    }

    public void gett(Thread thread){
        reentrantReadWriteLock.readLock().lock();

        long start = System.currentTimeMillis();
        try {
            while(System.currentTimeMillis() - start <=2){
                System.out.println(thread.getName()+"doing2");
            }
            System.out.println(thread.getName()+"done2");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
