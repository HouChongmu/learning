package com.yolyn.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/29 10:12 PM
 * @project netty-learning
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception{
        String str="hello yolyn";
        FileOutputStream fileOutputStream = new FileOutputStream("");
        //通过fileOutputStream获取对应的FileChannel
        //这个fileChannel真是类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());
        //对byteBuffer进行flip
        byteBuffer.flip();
        //将byteBuffer数据写入到FileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
