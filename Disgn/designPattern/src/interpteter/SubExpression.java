package interpteter;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
//相减的解释器
public class SubExpression extends  SymbolExpression{

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }
    //求出left和right 表达式相减后的结果
    public  int interpreter(HashMap<String,Integer> var){

        return  super.left.interpreter(var)-super.right.interpreter(var);
    }
}
