package mediator.smarthouse;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
public abstract class Mediator {
    //将给中介者对象，加入到集合中
    public  abstract  void  Register(String colleagueName,Colleague colleague);
    //
    public abstract void GetMessage(int stateChange,String colleague);
    public  abstract  void SendMessage();

}
