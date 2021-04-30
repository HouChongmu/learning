package data;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/30 22:10
 * @project lambda-learning
 */
public class Person {
    public String name;
    public int age;

    public Person() {
        System.out.println("Person类的无参构造方法执行了");
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }
}
