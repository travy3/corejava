package com.zyu.corejava.thread;

/**
 * Created by chenjie on 2016/2/15.
 */
public class ThreadTestDemo3 {

    public static void main(String[] args){
        MyThread1 thread1 = new MyThread1();
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("3 Series");
        }

    }

}
class MyThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(" bmw "+i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
