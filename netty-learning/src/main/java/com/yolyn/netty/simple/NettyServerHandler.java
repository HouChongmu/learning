package com.yolyn.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 11:38 AM
 * @project netty-learning
 * 1. 自定义一个Handler需要继承Netty规定好的某个HandlerAdapter
 * 2。 这是自定义的Handler才能成为一个netty的Handler，因为Netty里面有很多规范去遵守
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据（这里可以读取客户端发送的消息）
     *
     * @param ctx 上下文对象，含有管道pipeline（注重数据的处理），和通道（注重数据的读写），地址
     * @param msg 客户端发送的数据，默认是Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server read thread name is:"+Thread.currentThread().getName());

        System.out.println("server ctx="+ctx);
        //将msg转成一个ByteBuf
        ByteBuf buffer=(ByteBuf) msg;
        System.out.println("client msg is:" +buffer.toString(CharsetUtil.UTF_8));
        System.out.println("client address is"+ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入缓冲并刷新=write+flush
        //一般对发送的数据要进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,client",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
