package com.zyu.corejava.thread;

import java.util.Random;

/**
 * Created by chenjie on 2016/2/16.
 */
public class ThreadTestDemo52 {
    public static void main(String[] args){

        Send send = new Send();

        Receiver receiver = new Receiver(send);

        Thread t1 = new Thread(send);
        Thread t2 = new Thread(receiver);
        t2.setDaemon(true);

        t1.start();
        t2.start();

    }

}
class Send implements Runnable{
    int theValue;
    boolean flag;

    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (this){
                while (flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                theValue = new Random().nextInt(1000);
                System.out.println("send thevalue is:"+theValue);
                flag = true;
                this.notify();
            }
        }
    }
}

class Receiver implements Runnable{

    private Send send;

    public Receiver(Send send){
        this.send = send;
    }

    public void run() {

        System.out.println("asd");
        while (true){
            synchronized (send){
                while (!send.flag){
                    try {
                        send.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(this.toString());
                System.out.println("receiver the value is:"+send.theValue);
                send.flag = false;
                send.notify();
            }
        }
    }
}
