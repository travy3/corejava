package com.zyu.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by travy on 2016/2/23.
 */
public class NIOServer {

    private int flag = 0;
    private int BLOCK = 4096;

    private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);

    private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);

    private Selector selector;

    public NIOServer(int port) throws IOException{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();

        //服务绑定
        serverSocket.bind(new InetSocketAddress(port));

        //通过open找到selector

        selector = Selector.open();

        //向Selector注册Channel及我们有兴趣的事件

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server Start......");




    }

    public void listen() throws IOException {

        //不断轮询

        while(true){
            //选择一组键，并且相应的通道已经打开   　
            //Selector通过select方法通知我们我们感兴趣的事件发生了。
            selector.select();
            //返回次选择器的已选择键集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                //我们从这些key中的channel()方法中取得我们刚刚注册的channel。
                SelectionKey selectionKey = iterator.next();
                //一个key被处理完成后，就都被从就绪关键字（ready keys）列表中除去
                iterator.remove();
                handleKey(selectionKey);
            }
        }

    }


    public void handleKey(SelectionKey selectionKey) throws IOException {

        ServerSocketChannel serverChannel =null;

        SocketChannel clientChannel = null;

        String receiveText ;

        String sendText;

        int count=0;

        // 测试此键的通道是否已准备好接受新的套接字连接。

        if(selectionKey.isAcceptable()){
            //返回为之创建次键值通道
            serverChannel = (ServerSocketChannel) selectionKey.channel();
            //接受到此通道套接字的连接
            //默认阻塞
            System.out.println("服务器监听拦截端口已开启");
            clientChannel = serverChannel.accept();
            clientChannel.configureBlocking(false);

            clientChannel.register(selector,SelectionKey.OP_READ);


        }else if(selectionKey.isReadable()){

            clientChannel = (SocketChannel) selectionKey.channel();

            receiveBuffer.clear();

            count = clientChannel.read(receiveBuffer);

            if(count > 0 ){
                receiveText = new String(receiveBuffer.array(),0,count);
                System.out.println("服务器端接收客户端数据:"+receiveText);
                clientChannel.register(selector, SelectionKey.OP_WRITE);
            }


        }else if(selectionKey.isWritable()){

            clientChannel = (SocketChannel) selectionKey.channel();

            sendBuffer.clear();
            sendText = "message from server:" + flag++;
            sendBuffer.put(sendText.getBytes());

            sendBuffer.flip();

            clientChannel.write(sendBuffer);

            System.out.println("服务端向客户端发送数据:"+sendText);
            clientChannel.register(selector, SelectionKey.OP_READ);
        }


    }

    public static void main(String[] args) throws IOException {

        int port = 8888;
        NIOServer server = new NIOServer(port);
        server.listen();
    }

}
