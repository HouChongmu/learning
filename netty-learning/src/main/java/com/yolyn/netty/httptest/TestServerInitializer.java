package com.yolyn.netty.httptest;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 10:45 PM
 * @project netty-learning
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //1. 向管道加入处理器
        ChannelPipeline channelPipeline = ch.pipeline();
        //加入一个netty提供的httpServerCodec 处理http的编码解码器
        channelPipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        channelPipeline.addLast("MyHttpServiceHandler", new TestHttpServerHandler());
    }
}
