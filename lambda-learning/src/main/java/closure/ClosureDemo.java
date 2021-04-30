package closure;

import java.util.function.Supplier;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/7/3 21:52
 * @project lambda-learning
 * 一般来说方法中的变量用完后会被销毁，但是闭包会延长其生命周期，所以调用完getNumber方法后还能调用get方法获取num值
 * 而且lambda中的变量会默认加final修饰
 */
public class ClosureDemo {
    public static void main(String[] args) {
        int n = getNumber().get();
    }

    private static Supplier<Integer> getNumber() {
        int num = 10;
//        num++;
        return () -> {
            return num;
        };
    }

}
