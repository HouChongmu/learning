package syntax;

import interfaces.LambdaNoneReturnSingleParameter;
import interfaces.LambdaSIngleReturnMultiParameter;
import interfaces.LambdaSingleReturnSingleParameter;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/29 21:51
 * @project lambda-learning
 * 语法精简：
 * 1. 参数：
 * 由于在接口的抽象方法中，已经定义了参数的数量和类型，所以在lambda表达式中，参数的类型可以省略
 * 备注：如果需要省略类型，则每一个参数的类型都要省略，千万不要出现省略一个参数类型，另一个不省略
 * <p>
 * <p>
 * 2. 参数小括号
 * 如果参数列表中，参数的数量只有一个，此时小括号可以省略
 * <p>
 * <p>
 * 3. 方法大括号
 * 如果方法体中只有一条语句，此时大括号可以省略
 * <p>
 * <p>
 * 4. 如果方法体中唯一的语句是一个返回语句，在省略大括号的同时也必须省略return
 */
public class Syntax2 {
    public static void main(String[] args) {
        //语法精简1
        LambdaSIngleReturnMultiParameter lambda1 = (a, b) -> {
            return a + b;
        };
    }

    //语法精简2
    LambdaNoneReturnSingleParameter lambda2 = a -> {
        System.out.println(a);
    };

    //语法精简3
    LambdaNoneReturnSingleParameter lambda3 = a -> System.out.println(a);

    //语法精简4
    LambdaSingleReturnSingleParameter lambda4 = a -> a + 10;


    //例子
    LambdaSIngleReturnMultiParameter lambda5 = (a, b) -> a + b;
}
