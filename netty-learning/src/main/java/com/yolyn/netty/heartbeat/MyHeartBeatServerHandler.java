package com.yolyn.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/25 8:52 PM
 * @project netty-learning
 */
public class MyHeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent) evt;
            switch (event.state()){
                case READER_IDLE:
                    System.out.println("读空闲");
                     break;
                case WRITER_IDLE:
                    System.out.println("写空闲");
                    break;
                case ALL_IDLE:
                    System.out.println("读写空闲");
            }
        }
    }
}
