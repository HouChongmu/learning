package com.yolyn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/4 15:02
 * @project stream-learning
 */
public class DataSourceDem {
    //数据源的获取
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        Stream<Integer> stream=list.stream();
        Stream<Integer> stream1=list.parallelStream();
        /**
         * 上面两种方法都能获取集合的数据源，
         * 不同点在于stream方法获取的数据源是串行的，而parallelStream获取的数据源是并行的，
         * parallelStream内部集成了多个线程对流中的数据操作，效率更高。
         */
        //通过arrays工具类中的stream方法获取数据源为数组的流
//        IntStream stream2= Arrays.stream()
    }
}
