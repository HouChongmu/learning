package com.yolyn.netty.stickyTcpFigure;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/29 7:31 PM
 * @project netty-learning
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();//获取可读的字节长度（返回当前 readerIndex 的 int 值，并将 readerIndex 增加 4）
        byte[] msgBytes = new byte[length];
        in.readBytes(msgBytes);
//封装成 MessageProtocol 对象，放入 out， 传递下一个handler业务处理
        ProtocalMsg msg = new ProtocalMsg();
        msg.setLength(length);
        msg.setContent(msgBytes);

        out.add(msg);
    }
}
