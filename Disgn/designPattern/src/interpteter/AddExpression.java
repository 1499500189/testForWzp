package interpteter;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
//加法解释器
public class AddExpression extends  SymbolExpression{
    //构造器  ，调用父类的构造器
    public AddExpression(Expression left, Expression right) {
          super(left, right);
    }
    //处理相加
    //var仍然是{a=10,b=20}

    public  int interpreter(HashMap<String,Integer> var){
        //super。left。interpreter（var） ：返回left表达式对应的值  a=10
        //super.right.interpreter(var:  返回right表达式对应的值 b =20

        System.out.println(super.left);
        System.out.println(left.interpreter(var));
        System.out.println(super.left.interpreter(var));
        return  super.left.interpreter(var) +super.right.interpreter(var);
    }

}
