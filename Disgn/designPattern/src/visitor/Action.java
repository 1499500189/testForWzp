package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public abstract class Action {
    //得到男性的测评
    public  abstract  void getManResult(Man man);
    //得到女性的测评
    public  abstract  void getWomanResult(Woman woman);


}
