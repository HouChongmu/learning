package com.yolyn.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/11 9:41 PM
 * @project netty-learning
 */
public class GroupChatClient {
    private final String host;
    private final int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {

        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        //加入相关的handler
                        pipeline.addLast("decoder", new StringDecoder())
                                .addLast("encoder", new StringEncoder())
                                .addLast(new GroupChatClientHandler());
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("====>"+channelFuture.channel().remoteAddress()+"=======");
            //客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String next = scanner.next();
                //通过channel发送到服务器端
                channel.writeAndFlush(next+"\r\n");
            }
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args)throws Exception {
        GroupChatClient groupChatClient = new GroupChatClient("127.0.0.1", 7000);
        groupChatClient.run();
    }
}
