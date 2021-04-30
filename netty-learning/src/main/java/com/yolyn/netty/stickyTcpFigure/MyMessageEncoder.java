package com.yolyn.netty.stickyTcpFigure;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/29 7:41 PM
 * @project netty-learning
 */
public class MyMessageEncoder extends MessageToByteEncoder<ProtocalMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocalMsg msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode 方法被调用");
        out.writeInt(msg.getLength());//设置容量（在当前 writerIndex 处写入一个 int 值，并将 writerIndex 增加 4）
        out.writeBytes(msg.getContent());
    }
}
