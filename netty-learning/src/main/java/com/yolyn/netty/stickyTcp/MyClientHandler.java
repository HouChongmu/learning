package com.yolyn.netty.stickyTcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/29 6:48 PM
 * @project netty-learning
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String s = new String(bytes, CharsetUtil.UTF_8);
        System.out.println(++count + "====>服务器返回：" + s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 1; i < 10; i++) {
            Channel channel = ctx.channel();
            ByteBuf byteBuf = Unpooled.copiedBuffer(String.valueOf(i) + "==>hello server", CharsetUtil.UTF_8);
            channel.writeAndFlush(byteBuf);
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
