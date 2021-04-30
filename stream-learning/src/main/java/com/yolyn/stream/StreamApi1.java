package com.yolyn.stream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.yolyn.stream.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/4 18:04
 * @project stream-learning
 * <p>
 * Stream的三个操作步骤
 * 1. 创建Stream
 * 2. 中间操作
 * 3. 终止操作
 */
public class StreamApi1 {
    /**
     * 创建stream
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        //1. 通过collection系列结合提供的stream
//        List<String> list = new ArrayList<>();
//        Stream<String> stream1 = list.stream();
//        Stream<String> stream2 = list.parallelStream();
//
//        //2. 通过Arrays中的静态方法获取数组流
//        Employee[] employees = new Employee[10];
//        Stream<Employee> stream3 = Arrays.stream(employees);
//
//        //3. 通过Stream类中的静态方法
//        Stream<String> stream4 = Stream.of("aa", "bb", "cc");
//
//        //4. 创建无限流
//        //迭代
//        //seed:起始值
//        Stream<Integer> stream5 = Stream.iterate(0, (x) -> x + 2);
//        //中间操作+终止操作
//        stream5.limit(10).forEach(System.out::println);

//        long startTime=System.currentTimeMillis();
//        Thread.sleep(6000L);
//        long endTime=System.currentTimeMillis();
//        System.out.println("====>"+(endTime-startTime)/1000);
        //生成
//        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);

        String resp="{\n" +
                "  \"requestParam\": \"{\\\"campaignId\\\":\\\"1000450010\\\",\\\"endorseItems\\\":[{\\\"newValue\\\":{\\\"flightNo\\\":\\\"CA123\\\",\\\"flightDate\\\":\\\"20210429171700\\\",\\\"eTicketNo\\\":\\\"7811234567890\\\"},\\\"oldValue\\\":{\\\"flightNo\\\":\\\"CA1234\\\",\\\"flightDate\\\":\\\"20210428171700\\\"},\\\"type\\\":\\\"301\\\"},{\\\"newValue\\\":{\\\"expireDate\\\":\\\"20210429171700\\\",\\\"effectiveDate\\\":\\\"20210429151700\\\"},\\\"oldValue\\\":{\\\"expireDate\\\":\\\"20210428171700\\\",\\\"effectiveDate\\\":\\\"20210428171700\\\"},\\\"type\\\":\\\"303\\\"}],\\\"extendInfo\\\":\\\"\\\",\\\"policyNo\\\":\\\"8273I0110282833704\\\",\\\"productPackageId\\\":\\\"51251110\\\"}\",\n" +
                "  \"requestNo\": \"BX202103251720540005528605\"\n" +
                "}";
        JSONObject jsonParam=JSONObject.parseObject(resp);
        JSONObject requestParam = JSONObject.parseObject(jsonParam.getString("requestParam"));
        JSONArray endorseItems = JSONObject.parseArray(requestParam.getString("endorseItems"));

    }
}
