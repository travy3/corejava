package com.zyu.corejava.nio;

import java.io.*;

/**
 * Created by travy on 2016/2/23.
 */
public class NormalTest2 {

    public static void main(String[] args){

        BufferedReader bufferedReader = null;

        try {
            FileReader reader = new FileReader("F:" + File.separator + "CCB_Ctrl.log");

            bufferedReader = new BufferedReader(reader);

            String str = null;
            try {
                while((str = bufferedReader.readLine()) != null){

                    System.out.println(str);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
