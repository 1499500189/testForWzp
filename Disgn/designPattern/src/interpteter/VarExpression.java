package interpteter;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
//变量的解释器
public class VarExpression extends  Expression{
    private  String key ;     //key=a,key =b,key=c

    public VarExpression(String key) {
        this.key = key;
    }

     //var就是能拿到的HashMap{a=10,b=20}
    //interpreter就是根据变量的名称 ，返回对应值
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}
