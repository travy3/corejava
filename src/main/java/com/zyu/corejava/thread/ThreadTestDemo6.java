package com.zyu.corejava.thread;

import java.util.Random;

/**
 * Created by chenjie on 2016/2/18.
 */
public class ThreadTestDemo6 {
    public static void main(String[] args){

        final Business business = new Business();

        Thread t1 = new Thread(){
            public void run(){
                business.send();
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                business.rec();
            }
        };

        t2.setDaemon(true);
        t1.start();
        t2.start();
    }


}
class Business{

    private int theValue;
    private boolean flag;

    public void send(){

        System.out.println("send");
        for (int i = 0; i < 5; i++) {
            synchronized (this){
                System.out.println("in");
                while(flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                theValue = new Random().nextInt(100);
                System.out.println("send the value is :"+theValue);
                flag = true;
                this.notify();
            }
        }
    }

    public void rec(){
        System.out.println("rec");

        while(true){
            synchronized (this){
                System.out.println("in2");
                while (!flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("recevier the value is:"+theValue);
                flag = false;
                this.notify();
            }
        }
    }
}