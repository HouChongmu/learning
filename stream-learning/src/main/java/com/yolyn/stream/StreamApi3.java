package com.yolyn.stream;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import com.yolyn.stream.model.Person;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/4 21:02
 * @project stream-learning
 * Stream的查找与匹配
 * allMatch:检查是否匹配所有元素
 * anyMatch：检查是否至少匹配一个元素
 * noneMatch-检查是否没有匹配所有元素
 * findFirst：返回第一个元素
 * findAny：返回当前流中任意元素
 * count：返回流中元素的总个数
 * max：返回流中最大值
 * min：返回流中最小值
 * <p>
 * <p>
 * reduce：归约-可以将流中元素反复结合起来得到一个值
 * collect: 收集，将流转换为其他形式，接受一个Collector接口的实现，用于给Stream中元素做汇总的方法
 */
public class StreamApi3 {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("hyl", 12));
        list.add(new Person("mzj", 12));
        list.add(new Person("mfx", 33));
        list.add(new Person("hyl", 3));
        boolean match = list.stream().anyMatch(p -> p.age > 10);
        boolean match1 = list.stream().allMatch(p -> p.age > 10);


        Optional<Person> optionalPerson = list.stream().findFirst();
        Person p = optionalPerson.orElse(new Person("orElse", 333));//orElse如果没有值就用参数里面的
        System.out.println(p);
        System.out.println(match);


        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        //下面reduce第一个参数 0为起始值
        Integer sum = list1.stream().reduce(0, (x, y) -> x + y);//开始0赋给x，1赋给y;下一步x+y赋给x，2赋给y
        System.out.println(sum);

        Optional<Integer> optionalInteger = list.stream().map(Person::getAge).reduce(Integer::sum);//map操作最终会组合成一个流
        System.out.println(optionalInteger.get());


        List<String> names = list.stream().map(Person::getName).collect(Collectors.toList());
        Set<String> nameSets = list.stream().map(Person::getName).collect(Collectors.toSet());
        HashSet<String> hs = list.stream().map(Person::getName).collect(Collectors.toCollection(HashSet::new));
        //总和
        list.stream().map(Person::getAge).collect(Collectors.counting());
        hs.forEach(System.out::println);
        //最大值
        Optional<Person> max = list.stream().max((e1, e2) -> e2.age - e1.age);
        list.stream().collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())));
        System.out.println("max:" + max.get());

        //分组
        //按照年龄分组
        Map<Integer, List<Person>> groups = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(groups);

        //多级分组
        Map<Integer, Map<String, List<Person>>> groupss = list.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.groupingBy(Person::getName)));

        //分片
        Map<Boolean, List<Person>> persons = list.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 20));
        System.out.println(persons);

        //牛逼式收集
        IntSummaryStatistics iss=list.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(iss.getMax());
        System.out.println(iss.getAverage());

        //拼接字符串
        list.stream().map(Person::getName).collect(Collectors.joining());

    }
}
