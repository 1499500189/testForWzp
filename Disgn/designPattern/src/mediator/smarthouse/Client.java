package mediator.smarthouse;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
public class Client {
    public static void main(String[] args) {
        //创建一个中介者对象
        Mediator mediator = new ConcreteMediator();
        //创建Alarm并且加入到ConcreteMediator对象 的hashMap
        Alarm alarm = new Alarm(mediator, "alarm");
        CoffeeMachine coffeeMachine = new CoffeeMachine(mediator, "coffeeMachine");
        //创建了Curtains ，并且 加入到ConcreteMediator对象的HashMap
        Curtains curtains = new Curtains(mediator, "curtains");
        TV tv = new TV(mediator, "TV");
        //让闹钟发出消息
        alarm.SendMessage(0);
    }
}
