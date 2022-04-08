package com.zyu.corejava.thread;

import java.util.concurrent.*;


public class BlockingQueueTest {

    public static void main(String[] args) {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
        ExecutorService executorService  = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {

                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName()
                                    + "准备放数据!");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName()
                                    + "已经放了数据，" + "队列目前有" + queue.size()
                                    + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()
                                + "准备取数据!");
                        int tmp = queue.take();
                        System.out.println(Thread.currentThread().getName()
                                + "已经取走数据，数据是" + tmp + "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
