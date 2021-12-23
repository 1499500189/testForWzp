package mediator.smarthouse;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
//具体的同事类
public class Alarm  extends  Colleague{
    //构造器
    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
        //在创建Alarm同事对象的时，将自己放入到ConcreteMediator对象中{集合}
        mediator.Register(name,this);
    }
   public  void SendAlarm(int stateChange){
        SendMessage(stateChange);
     }

    @Override
    public void SendMessage(int stateChange) {
        //SendMessage(stateChange);
        //调用的是中介者对象的getMessage
        this.GetMediator().GetMessage(stateChange,this.name);

    }
}
