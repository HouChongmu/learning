package syntax;

import interfaces.LambdaSingleReturnSingleParameter;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/29 22:12
 * @project lambda-learning
 * <p>
 * 方法应用：
 * 可以快速将一个Lambda表达式的实现指向一个已经实现的方法
 * 语法：方法的隶属者::方法名
 * 静态方法的隶属者是类，非静态方法的隶属者是实例
 * <p>
 * 注意：
 * 1. 参数数量和类型一定要和接口中定义的方法一致
 * 2. 返回值的类型也一定要和接口中定义的方法一致
 */
public class Syntax3 {
    public static void main(String[] args) {
        LambdaSingleReturnSingleParameter lambda1 = a -> changeA(a);
        //方法引用
        LambdaSingleReturnSingleParameter lambda = Syntax3::changeA;

    }

    public static int changeA(int a) {
        return a + 10;
    }
}
