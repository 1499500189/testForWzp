package interpteter;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
//抽象类表达式，通过HashMap的键值对 ，可以获取到变量的值
public  abstract  class Expression {
    //a+b-c
    //解释公式和数值，key就是公式(表达式)   参数  [a,b,c] ，   value就是具体的值
    //HashMap{a=10 ， b=20}
    public  abstract int interpreter(HashMap<String,Integer> var);
}
