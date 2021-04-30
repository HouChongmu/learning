package com.yolyn.stream;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/4 18:20
 * @project stream-learning
 * 中间操作
 * 1.
 * 筛选与切片
 * filter：接受lambda，从流中排除某些元素
 * limit：截断流，使其元素不超过给定数量
 * skip(n):跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，返回一个空流，与limit(n)互补
 * distinct：筛选，通过流所生成的元素的hashCode和equals去除重复元素
 * <p>
 * 注意的是中间操作时除非流水线上触发终止操作，否则中间操作不会执行任何的处理，而在终止时一次性处理全部，称为“惰性求值”
 * <p>
 * <p>
 * 2.
 * map: 接受lambda 将元素转换成其他形式或提取信息，接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
 * flatmap：  接收一个函数作为参数，将流中的每个值都换成一个流，然后把所有流连接成一个流
 * <p>
 * 3.
 * 排序
 * sorted: 自然排序（按照字母顺序）
 * sorted(Comparator com) ： 定制排序
 */
public class StreamApi2 {
    public static void main(String[] args) {
        Stream<String> stream4 = Stream.of("aa", "bb", "cc");
        List<String> list = stream4.filter((a) -> a.equals("aa")).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(list));
//
//        List<String> list1 = Arrays.asList("asd", "fgh", "jas");
//        list1.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
//
////--------------------------------------------------------------------------
//        Stream<Stream<Character>> stream = list1.stream()
//                .map(StreamApi2::filterCharacter);
//        stream.forEach(sm -> {
//            sm.forEach(System.out::println);
//        });
//
//        //上面效果等同于下面
//        Stream<Character> sm = list1.stream()
//                .flatMap(StreamApi2::filterCharacter);
//        sm.forEach(System.out::println);
////-----------------------------------------------------------------------------------
//        //定制排序
//        sm.sorted((e1, e2) -> e2.compareTo(e2));
//    }
//
//    public static Stream<Character> filterCharacter(String str) {
//        List<Character> list = new ArrayList<>();
//        for (Character ch : str.toCharArray()) {
//            list.add(ch);
//        }
//        return list.stream();
    }

}
