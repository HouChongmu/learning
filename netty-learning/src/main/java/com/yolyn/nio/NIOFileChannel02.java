package com.yolyn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/29 10:36 PM
 * @project netty-learning
 * <p>
 * channel应用实例2
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {

        File file = new File("");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将通道的数据读入到通道中
        fileChannel.read(byteBuffer);
        //将byteBuffer的字节数据转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
