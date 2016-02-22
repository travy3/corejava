package com.zyu.corejava.io;

import java.io.IOException;
import java.net.*;

/**
 * Created by chenjie on 2016/2/22.
 */
public class UDPClient {
    public static void main(String[] args){
        try {
            DatagramSocket datagramSocket = new DatagramSocket();

            try {
                InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

                String str ="hello";

                byte[] data = str.getBytes();

                DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,9000);

                try {
                    datagramSocket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
