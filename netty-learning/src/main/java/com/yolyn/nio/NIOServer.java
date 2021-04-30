package com.yolyn.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/4 8:37 PM
 * @project netty-learning
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector，关心的事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) {
                //没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            //如果返回>0,获取到相关的selectionKeys的集合
            //如果返回>0，表示已经获取到关注的事件
            //selector.selectedKeys()返回关注的事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                //根据key对应的通道发生的事件做相应的处理
                if (selectionKey.isAcceptable()) {

                    //如果是OP_ACCEPT，有新的客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功,socketChannel:"+socketChannel.hashCode());
                    //将socketChannel注册到selector,关注事件为READ，同时给channel关联一个buffer,会返回一个key
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (selectionKey.isReadable()) {
                    //发生读事件，通过selectionKey反向获取对应channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端" + new String(buffer.array()));
                }
                //手动从集合中移除当前selectionKey，防止重复操作
                keyIterator.remove();

            }
        }
    }
}
