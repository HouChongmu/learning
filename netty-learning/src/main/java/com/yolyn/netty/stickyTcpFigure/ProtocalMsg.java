package com.yolyn.netty.stickyTcpFigure;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/8/29 7:15 PM
 * @project netty-learning
 */
public class ProtocalMsg {
    private int length;
    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
