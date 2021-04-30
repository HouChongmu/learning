/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/29 21:23
 * @project lambda-learning
 */
public class Program {
    public static void main(String[] args) {
        //1. 使用接口实现类的
        Comparator comparator = new MyComparator();

        //2. 使用匿名内部类
        Comparator comparator1 = new Comparator() {
            public int compare(int a, int b) {
                return a - b;
            }
        };
        //3. 使用lambda表达式
        Comparator comparator2 = (a, b) -> a - b;
    }

}

class MyComparator implements Comparator {

    public int compare(int a, int b) {
        return a - b;
    }
}

@FunctionalInterface
interface Comparator {
    int compare(int a, int b);
}
