package exercise;

import data.Person;

import java.util.ArrayList;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/30 22:22
 * @project lambda-learning
 * 需求：一直在一个ArrayList中有若干个Person对象，将这些Person对象按照年龄进行降序排序
 */
public class Exercise1 {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("hyl", 12));
        list.add(new Person("mzj", 13));
        list.add(new Person("mfx", 33));
        list.add(new Person("hyl", 3));
//        list.sort((p1, p2) -> {
//            return p2.age - p1.age;
//        });
        list.sort((p1, p2) -> p2.age - p1.age);
        System.out.println(list);
    }
}
