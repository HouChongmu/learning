package com.yolyn.nio;

import java.nio.IntBuffer;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/29 9:08 PM
 * @project netty-learning
 */
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer=IntBuffer.allocate(5);
        for (int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i*2);
        }
        intBuffer.flip();//读写切换
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
