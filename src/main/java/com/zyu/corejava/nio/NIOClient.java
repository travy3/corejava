package com.zyu.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chenjie on 2016/2/24.
 */
public class NIOClient {

    private static int flag = 0 ;

    private static int BLOCK = 4096;

    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);

    private static ByteBuffer recevieBuffer = ByteBuffer.allocate(BLOCK);

    private static final InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost",8888);

    public static void main(String[] args) throws IOException{



        //获取通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //创建选择器
        Selector selector = Selector.open();
        //注册通道
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        socketChannel.connect(SERVER_ADDRESS);


        SocketChannel clientChannel;
        int count = 0;
        String receiveText;
        String sendText;
        while(true){

            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isConnectable()) {
                    System.out.println("connect client..");
                    clientChannel = (SocketChannel) selectionKey.channel();
                    if(clientChannel.isConnectionPending()){
                        clientChannel.finishConnect();
                        System.out.println("完成连接");
                        sendBuffer.clear();
                        sendBuffer.put("Hello Server".getBytes());
                        sendBuffer.flip();
                        clientChannel.write(sendBuffer);
                    }
                    clientChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){

                    clientChannel = (SocketChannel) selectionKey.channel();
                    recevieBuffer.clear();
                    count = clientChannel.read(recevieBuffer);
                    if (count > 0) {
                        receiveText = new String(recevieBuffer.array(),0,count);
                        System.out.println("客户端接收服务端数据"+receiveText);
                        clientChannel.register(selector,SelectionKey.OP_WRITE);
                    }

                }else if(selectionKey.isWritable()){
                    sendBuffer.clear();

                    clientChannel  = (SocketChannel) selectionKey.channel();

                    sendText ="message from client:"+flag++;

                    sendBuffer.put(sendText.getBytes());

                    sendBuffer.flip();

                    clientChannel.write(sendBuffer);
                    System.out.println("客户端向服务器发送数据:"+sendText);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
            }
            selectionKeys.clear();
        }
    }
}
