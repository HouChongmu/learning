package syntax;

import interfaces.LambdaNoneReturnMultiParameter;
import interfaces.LambdaNoneReturnNoneParameter;
import interfaces.LambdaSIngleReturnMultiParameter;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/6/29 21:41
 * @project lambda-learning
 */
public class Syntax1 {
    public static void main(String[] args) {
        LambdaNoneReturnNoneParameter lambda1=()->{
            System.out.println("hello world");
        };
        lambda1.test();

        LambdaNoneReturnMultiParameter lambda2=(int a,int b)->{
            System.out.println(a+b);
        };
        lambda2.test(1,2);

        LambdaSIngleReturnMultiParameter lambda3=(int a ,int b)->{
            return a+b;
        };
        System.out.println(lambda3.test(2,3));
    }
}
