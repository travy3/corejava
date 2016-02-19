package com.zyu.corejava.thread;

import java.util.concurrent.*;

/**
 * Created by chenjie on 2016/2/18.
 */
public class ThreadTestDemo8 {

    public static void main(String[] args){

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new Callable<String>() {

            public String call() throws Exception {
                return "BMW 3";
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        threadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("borning");
            }
        },0,2, TimeUnit.SECONDS);
    }
}
