package com.yolyn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/30 9:07 PM
 * @project netty-learning
 */
public class NIOFIleChannel04 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("pom.xml");
        FileOutputStream fileOutputStream = new FileOutputStream("abc.xml");

        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        destChannel.transferFrom(sourceChannel,0,sourceChannel.size());

        //关闭相关的通道和流
        fileInputStream.close();
        fileOutputStream.close();
        sourceChannel.close();
        destChannel.close();

    }
}
