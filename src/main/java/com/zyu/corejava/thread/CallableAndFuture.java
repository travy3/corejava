package com.zyu.corejava.thread;

import java.util.concurrent.*;

/**
 * Created by chenjie on 2016/2/19.
 */
public class CallableAndFuture {

    public static void main(String[] args){

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //CompletionService callable返回的数据，临时存放起来,提供 future后期获取
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);



        for (int i = 0; i < 10; i++) {
            final Integer seq = i+1;

            completionService.submit(new Callable<Integer>() {
                public Integer call() throws Exception {

                    Thread.sleep((long) (Math.random()*1000));
                    return  seq;
                };
            });
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> future = completionService.take();
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
