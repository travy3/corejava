package com.zyu.corejava.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenjie on 2016/2/15.
 */
public class ThreadTestDemo4 {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    private Lock lock = new ReentrantLock();

    public static void main(String[] args){

        final ThreadTestDemo4 test = new ThreadTestDemo4();


        new Thread(new Runnable() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }).start();

        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            }
        }.start();


    }

    public void insert(Thread thread){

        lock.lock();

        try {
            System.out.println(thread.getName()+"得到了锁");

            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }

    }
}
//class Output{
//
//    private Lock lock = new ReentrantLock();
//
//}
