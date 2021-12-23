package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
//说明
    //1.这里我们使用到了双分派 ，即首先在客户端程序中，将具体状态作为参数传递Woman中(第一次分派)
    //2，然后woman类调用作为参数的"具体方法"中的方法getWomanResult，同时将自己(this)作为参数传入，
// 完成了的第二次分派
public class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
