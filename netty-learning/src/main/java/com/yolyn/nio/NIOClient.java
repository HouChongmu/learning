package com.yolyn.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/4 9:44 PM
 * @project netty-learning
 */
public class NIOClient {
    public static void main(String[] args) throws Exception{
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //如果一个 Channel 要注册到 Selector 中, 那么这个 Channel 必须是非阻塞的
        socketChannel.configureBlocking(false);
        //提供服务器ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其他工作");
            }
        }
        //连接成功
        String str="hello world";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据，将buffer数据写入channel
        socketChannel.write(buffer);
        System.in.read();
    }
}
