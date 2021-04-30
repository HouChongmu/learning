package com.yolyn.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 11:06 AM
 * @project netty-learning
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //1. 创建两个线程组：BossGroup和WorkGroup，这两个都是无限循环
        //2. bossgroup只是处理连接请求，真正和客户端业务处理会交给workGroup去做
        //3. bossGroup和workerGroup含有的子线程（NioEventLoop）的个数默认是cpu核数*2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式变成进行设置
            bootstrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .handler(null)//给bossGroup加handler
                    .childHandler(new ChannelInitializer< SocketChannel>() {//创建一个通道初始化对象
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //可以使用一个集合管理SocketChannel，在推送消息时可以讲业务加入到各个channel对应的NioEventLoop的taskQueue或ScheduleTaskQueue中
                            System.out.println("客户端socketChannel：" + ch.hashCode());
                            ch.pipeline().addLast(new NettyServerAsyncHandler());//向pipeLine添加一个处理器
                        }
                    });//给我们的workerGroup的EventLoop对应的管道设置处理器

            System.out.println("server is ready");
            //绑定一个端口并同步，生成一个ChannelFuture对象,启动服务器
            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    System.out.println("监听端口成功");
                } else {
                    System.out.println("监听端口失败");
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
