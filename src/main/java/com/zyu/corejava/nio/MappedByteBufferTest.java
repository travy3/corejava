package com.zyu.corejava.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by travy on 2016/2/23.
 */
public class MappedByteBufferTest {

    public static void main(String[] args) {
        File file = new File("F:" + File.separator + "CCB_Ctrl.log");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel channel = inputStream.getChannel();

            MappedByteBuffer mappedByteBuffer = null;

            try {
                mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY,0,file.length());

                byte[] data  = new byte[(int) file.length()];

                int index = 0;
                while (mappedByteBuffer.hasRemaining()){
                    data[index++] = mappedByteBuffer.get();
                }
                System.out.println(new String(data));
                channel.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
