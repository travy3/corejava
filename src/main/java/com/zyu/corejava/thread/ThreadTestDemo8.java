package com.zyu.corejava.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenjie on 2016/2/18.
 */
public class ThreadTestDemo8 {

    public static void main(String[] args){

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);

        threadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("borning");
            }
        },0,2, TimeUnit.SECONDS);
    }
}
