package method;

import data.Employee;
import interfaces.LambdaNoneReturnSingleParameter;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/4 17:04
 * @project lambda-learning
 * lambda默认有几个默认接口
 * 1. 消费型接口
 * Consumer<T>
 * void accept(T t)
 * <p>
 * 2. 供给型接口
 * Supplier<T>
 * T get();
 * <p>
 * 3. 函数型接口
 * Function<T,R>
 * R apply(T t)
 * <p>
 * 4. 断言型接口
 * Predicate<T>
 * boolean test(T t)
 *
 * <p>
 * 若Lambda 体中的内容有方法已经实现了，可以使用方法应用
 * 主要有三种语法格式
 * 1. 对象::实例方法名
 * 2. 类::静态方法名
 * 3. 类::实例方法名
 * <p>
 * <p>
 * 注意：
 * 1. Lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * 2. 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用CLassName::method
 */
public class MethodRef {
    public static void main(String[] args) {
        /**
         * 对象::实例方法名
         */
        LambdaNoneReturnSingleParameter a = (b) -> System.out.println(b);

        //参数列表和返回值要一致
        LambdaNoneReturnSingleParameter c = System.out::println;


        Employee e = new Employee();
        Supplier<String> sup = () -> e.getName();
        String str = sup.get();

        Supplier<Integer> sup2 = e::getAge;//因为强制参数类型一直，所以实例::方法体的时候连请求参数都不需要声明
        Integer i = sup2.get();


        /**
         * 类::静态方法名
         */

        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com2 = Integer::compare;//参数类型和返回值必须默认一致，所以不用写


        /**
         * 类::实例方法名
         */
        BiPredicate<String,String> bi=(q,w)->q.equals(w);
        //这样使用有一个必要前提，第一个参数要作为方法的调用者，第二作为参数传递，如上面q调用equal，w是参数，就可以用下面的简化
        BiPredicate<String,String> bi2=String::equals;
    }
}
