package com.yolyn.netty.stickyTcpFigure;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/29 7:27 PM
 * @project netty-learning
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ProtocalMsg> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocalMsg msg) throws Exception {
        int len = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到消息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, Charset.forName("utf-8")));

        System.out.println("客户端接收消息数量=" + (++this.count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 10; i++) {
            String msg = "爱婧婧";
            byte[] msgBytes = msg.getBytes();
            int msgLength = msgBytes.length;

            ProtocalMsg protocalMsg = new ProtocalMsg();
            protocalMsg.setLength(msgLength);
            protocalMsg.setContent(msgBytes);
            ctx.writeAndFlush(protocalMsg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常消息=" + cause.getMessage());
        ctx.close();
    }
}
