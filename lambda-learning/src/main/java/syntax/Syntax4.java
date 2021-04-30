package syntax;

import data.Person;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/30 22:13
 * @project lambda-learning
 * <p>   构造器引用
 * 需求
 */
public class Syntax4 {
    public static void main(String[] args) {
        PersonCreater creater = () -> new Person();

        //构造方法的应用
        PersonCreater creater1 = Person::new;
        Person a = creater1.getPerson();

        PersonCreater2 creater2=Person::new;
        creater2.getPerson2("yolyn",24);

    }
}

interface PersonCreater {
    Person getPerson();
}

interface PersonCreater2 {
    Person getPerson2(String name, int age);
}