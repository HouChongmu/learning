package com.yolyn.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/9 10:07 PM
 * @project netty-learning
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    //定义一个channel组，管理所有的channel
    //全局事件执行器是一个单例
    private static ChannelGroup  channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel ch = ctx.channel();
        //遍历channelGroup，根据不同的情况发送不同的消息
        channelGroup.forEach(channel -> {
            if (ch != channel) {
                channel.writeAndFlush("客户：" + ch.remoteAddress() + "发送了消息：" + msg + "\n");
            } else {
                channel.writeAndFlush("自己发送了：" + msg);
            }
        });
    }

    /**
     * 表示连接建立后 第一个被执行
     * 将当前channel加入到channelGroup中
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端,该方法会将channelGroup中的所有channel遍历并发送消息
        channelGroup.writeAndFlush("客户端：" + channel.remoteAddress() + "加入聊天"+ simpleDateFormat.format(new Date())+"\n");
        channelGroup.add(channel);

    }

    /**
     * 表示channel处于活动的状态，提示xxx上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ":上线");
    }

    /**
     * 当channel处于非活动状态，提示xxx离线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ":离线");
    }

    /**
     * 断开连接  将xx用户离开消息推送给当前在线的用户
     * 会自动从channelGroup  remove掉
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("客户端：" + channel.remoteAddress() + "离开了\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
