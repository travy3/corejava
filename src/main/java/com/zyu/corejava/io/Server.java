package com.zyu.corejava.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenjie on 2016/2/22.
 */
public class Server {

    public static void main(String[] args){
        try {
            ServerSocket serverSocket  = new ServerSocket(9000);
            System.out.println("服务已经开启...");
            Socket socket = serverSocket.accept();
            System.out.println("接收到客户的访问");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                String str =br.readLine();
                System.out.println("客户端说:"+str);
                String str1 = in.readLine();
                if("end".equals(str1))break;
                out.println(str1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
