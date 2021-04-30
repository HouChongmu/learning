package com.yolyn.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 4:32 PM
 * @project netty-learning
 */
public class NettyClient {
    public static void main(String[] args){
        //客户端需要一个事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {

        //创建一个客户端启动对象,注意客户端使用的不是ServerBootStrap而是BootStrap
        Bootstrap bootstrap=new Bootstrap();
        //设置相关参数
        bootstrap.group(eventExecutors)//设置线程组
                .channel(NioSocketChannel.class)//设置客户端通道的实现类（反射）
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                    }
                });
        System.out.println("client is ok");
        //启动客户端去连接服务器端,关于channelFuture涉及到netty的异步模型
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
        //关闭通道进行监听，并不是马上关闭，而是监听到关闭通道事件之后再关闭
        channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            eventExecutors.shutdownGracefully();
        }

    }
}
