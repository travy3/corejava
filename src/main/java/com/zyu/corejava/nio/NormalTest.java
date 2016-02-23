package com.zyu.corejava.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by travy on 2016/2/23.
 */
public class NormalTest {

    public static void main(String[] args){

        File file = new File("F:" + File.separator + "11.txt");

//        if (!file.exists()) try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            try {
                while (in.read() !=-1){
                    System.out.print((char)in.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
