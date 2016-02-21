package com.zyu.corejava.io;

import java.io.*;
import java.net.Socket;

/**
 * Created by travy on 2016/2/21.
 */
public class Client {

    public static void main(String[] args){

        try {
            Socket socket   = new Socket("172.0.0.1",9000);
            //从客户端键盘读数据写到客户端  ---->适配器模式/装饰模式
            BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String str = br.readLine();
                if("exit".equals(str)) break;
                out.println(str);
                String str1 = in.readLine();
                System.out.println("服务器说："+str1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
