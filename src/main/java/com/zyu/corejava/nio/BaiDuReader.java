package com.zyu.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by travy on 2016/2/23.
 */
public class BaiDuReader {

    private SocketChannel socketChannel ;
    private Charset charSet = Charset.forName("GBK");

    public void readHTML(){

        InetSocketAddress address = new InetSocketAddress("www.baidu.com",80);

        try {
            socketChannel = SocketChannel.open(address);
            socketChannel.write(charSet.encode("GET " + "/ HTTP/1.1" + "\r\n\r\n"));

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (socketChannel.read(buffer)!= -1){
                buffer.flip();
                System.out.println(charSet.decode(buffer));
                buffer.clear();
            }
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){

        new BaiDuReader().readHTML();
    }
}
