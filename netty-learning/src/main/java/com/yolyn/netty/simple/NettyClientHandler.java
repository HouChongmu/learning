package com.yolyn.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 4:42 PM
 * @project netty-learning
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当通道就绪时触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx:"+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello ,server,this is client", CharsetUtil.UTF_8));
    }

    /**
     * 当通道有读取事件时会触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       ByteBuf buf= (ByteBuf)msg;
        System.out.println("server msg is :"+buf.toString(CharsetUtil.UTF_8));
        System.out.println("server address is" +ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
