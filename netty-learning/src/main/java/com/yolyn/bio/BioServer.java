package com.yolyn.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/28 10:47 PM
 * @project netty-learning
 *
 * 思路
 * 1. 创建一个线程池。
 * 2。 如果有客户端链接，就创建一个线程，与之通讯（单独写一个方法）
 *
 */
public class BioServer {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务启动了");
        while (true){
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket){
        try {
            byte[] bytes=new byte[1024];
            InputStream inputStream=socket.getInputStream();

            while (true){
                int read=inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }
        }catch (Exception e){

        }
    }

}
