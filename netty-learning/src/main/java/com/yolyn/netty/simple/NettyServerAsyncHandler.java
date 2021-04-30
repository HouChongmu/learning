package com.yolyn.netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 6:10 PM
 * @project netty-learning
 * 异步执行方案
 *
 */
public class NettyServerAsyncHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //假如这里有个非常耗时的业务：异步执行->提交该channel对应的NioEventLoop的taskQueue中
        //解决方案1：用户自定义的普通任务，channelRead马上会执行完成,任务会放在NioSocketChannel#eventLoop#taskQueen中
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client",CharsetUtil.UTF_8));
                System.out.println("go on.... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        //解决方案2：该任务是提交到scheduleTaskQueue中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello client",CharsetUtil.UTF_8));
                    System.out.println("go on... ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },5, TimeUnit.SECONDS);

        /**
         * 解决方案3：非当前Reactor线程调用Channel的各种方法
         *
         * 如再推送系统的业务线程中，根据用户的表示，找到对应的Channel的引用，然后调用Write类方法向该用户推送消息，就会进入到这类场景，
         * 最终的write会提交到对应的任务队列中被异步消费
         */
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("complete", CharsetUtil.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
