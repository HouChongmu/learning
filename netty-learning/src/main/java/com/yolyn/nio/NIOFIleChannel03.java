package com.yolyn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/30 8:51 PM
 * @project netty-learning
 */
public class NIOFIleChannel03 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream=new FileInputStream("pom.xml");
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("abc.xml");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        while (true){
            //一定不要忘了这个动作
//            byteBuffer.clear();//清空buffer
            int read=inputStreamChannel.read(byteBuffer);
            if (read==-1){
                break;
            }
            //将buffer中的数据写到另一个channel中去
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
