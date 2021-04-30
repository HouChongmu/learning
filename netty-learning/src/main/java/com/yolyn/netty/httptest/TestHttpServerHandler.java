package com.yolyn.netty.httptest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/8 10:44 PM
 * @project netty-learning
 */

/**
 * 范型制定客户端服务端数据传递的类型
 * HttpObject 客户端和服务器端相互通讯的数据被封装成的类型
 *
 * 因为http请求默认不会保持长连接所以每一次请求都会创建一个新的handler和pipeline
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            System.out.println("msg类型：" + msg.getClass());
            System.out.println("客户端地址：" + ctx.channel().remoteAddress());
            //回复信息给浏览器
            ByteBuf content = Unpooled.copiedBuffer("hello,this is server", CharsetUtil.UTF_8);
            //构造一个http响应，即httpResponse
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //将构建好的response返回

            //获取到
            HttpRequest httpRequest = (HttpRequest) msg;
            //获取 uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }
            ctx.writeAndFlush(response);
        }

    }
}
