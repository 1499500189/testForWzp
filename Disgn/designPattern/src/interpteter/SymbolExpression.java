package interpteter;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
//抽象运算符号解析器 ，这里 ，每个运算符号 ， 都只和自己左右两个数组有关系，
    //但左右两个数字有可能也是一个解析的结果，无论何种类型， 都是Expression类的实现类
public class SymbolExpression extends  Expression {
    protected  Expression left;
    protected  Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
     //因为SymbolExpression是让其子类来实现 ，因为interpreter 是一个默认的实现
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        System.out.println("执行");
        return 0;
    }
}
