package com.yolyn.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/4 16:40
 * @project stream-learning
 */
public class FinalOperationDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 20, 30, 40, 50, 60);
        List<Integer> result = list.stream().collect(Collectors.toList());
        //转成map  需要提供键和值的生成规则    ele / 10最为键
        Map<Integer,Integer> resultMap=list.stream().collect(Collectors.toMap(ele -> ele / 10, (ele) -> {
            return ele;
        }));
    }
}
