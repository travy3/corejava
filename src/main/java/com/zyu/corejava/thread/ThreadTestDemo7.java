package com.zyu.corejava.thread;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by chenjie on 2016/2/18.
 */
public class ThreadTestDemo7 {

    private static HashMap<Thread,Integer> hashMap = new HashMap<Thread, Integer>();
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private static ThreadLocal<String> threadLocal2 = new ThreadLocal<String>();

    public static void main(String[] args){

        for (int i = 0; i < 5; i++) {

            Thread t = new Thread(){
                public void run(){
                    int data = new Random().nextInt(100);
                    hashMap.put(Thread.currentThread(),data);
                    threadLocal.set(data);
                    threadLocal2.set("qwe");
                    System.out.println(Thread.currentThread().getName()+"put the data is:"+data);
                    A a1 = new A();
                    a1.getData();
                    B b1 = new B();
                    b1.getData();
                }
            };
            t.start();
        }

    }
    static class A{
        void getData(){
            int data = hashMap.get(Thread.currentThread());
            System.out.println(Thread.currentThread().getName()+"from A getData is:"+data+"from ThreadLocal"+threadLocal.get()+threadLocal2.get());
        }
    }
    static class B{
        void getData(){
            int data = hashMap.get(Thread.currentThread());
            System.out.println(Thread.currentThread().getName()+"from B getData is:"+data+"from ThreadLocal"+threadLocal.get());
        }
    }
}
