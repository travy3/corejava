package com.zyu.corejava.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chenjie on 2016/2/23.
 */
public class FileTest {

    public static void main(String[] args){
//        RandomAccessFile randomAccessFile = new RandomAccessFile("H:"+ File.separator+ "LOLCfg.ini");

        File file = new File("H:"+ File.separator+ "LOLCfg.ini");
        File file1 = new File("H:"+File.separator+"qweasd.txt");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            final FileChannel channel = inputStream.getChannel();
            final ByteBuffer buffer =ByteBuffer.allocate(1024);

            new Thread(){
                public void run(){
                    try {
                        System.out.println("当前线程准备开始读取通道1"+Thread.currentThread().getName());
                        int bytesRead  = channel.read(buffer);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (bytesRead != -1){
                            buffer.flip();
                            while(buffer.hasRemaining()){
                                System.out.print((char) buffer.get());
                            }
                            buffer.clear();
                            System.out.println("当前线程准备开始清除通道"+Thread.currentThread().getName());
                            bytesRead = channel.read(buffer);
                            System.out.println("当前线程准备开始读取通道2"+Thread.currentThread().getName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            new Thread(){
                public void run(){
                    try {
                        System.out.println("当前线程准备开始读取通道3"+Thread.currentThread().getName());
                        int bytesRead  = channel.read(buffer);
                        while (bytesRead != -1){
                            buffer.flip();
                            while(buffer.hasRemaining()){
                                System.out.print((char) buffer.get());
                            }
                            buffer.clear();
                            System.out.println("当前线程准备开始清除通道"+Thread.currentThread().getName());
                            bytesRead = channel.read(buffer);
                            System.out.println("当前线程准备开始读取通道4"+Thread.currentThread().getName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
