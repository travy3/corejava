package com.zyu.corejava.thread;

/**
 * Created by chenjie on 2016/2/15.
 */
public class ThreadTestDemo1 {
    public static void main(String[] args){
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    System.out.println("hello");
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    System.out.println("world");
                }

            }
        }).start();
    }
}
