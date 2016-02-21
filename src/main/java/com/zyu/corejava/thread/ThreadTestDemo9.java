package com.zyu.corejava.thread;

import java.util.concurrent.*;

/**
 * Created by chenjie on 2016/2/18.
 */
public class ThreadTestDemo9 {
    public static void main(String[] args){

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(5000);
                return "zyu";
            }
        });


        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
