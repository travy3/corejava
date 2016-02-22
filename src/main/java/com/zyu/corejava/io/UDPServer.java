package com.zyu.corejava.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by chenjie on 2016/2/22.
 */
public class UDPServer {
    public static void main(String[] args){

        try {
            DatagramSocket datagramSocket = new DatagramSocket(9000);
            while (true){
                byte[] data = new byte[1024];

                DatagramPacket packet = new DatagramPacket(data,data.length);

                try {
                    datagramSocket.receive(packet);
                    String result = new String(packet.getData(), packet.getOffset(),
                            packet.getLength());
                    System.out.println("result--->" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
