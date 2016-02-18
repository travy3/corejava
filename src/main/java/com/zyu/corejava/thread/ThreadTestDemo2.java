package com.zyu.corejava.thread;

/**
 * Created by chenjie on 2016/2/15.
 */
public class ThreadTestDemo2 {
    public static void main(String[] args){
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("bmw 3");
        }
    }


}
class MyThread extends  Thread{

    public void run(){
        for (int i = 0;i<10;i++){
            System.out.println("zyu");
        }
    }
}