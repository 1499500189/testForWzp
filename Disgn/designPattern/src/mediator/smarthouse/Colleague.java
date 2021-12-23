package mediator.smarthouse;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
//同事抽象类
public  abstract  class Colleague {
    private  Mediator  mediator;
    public  String name ;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    public  Mediator  GetMediator(){
        return  this.mediator;
    }
    public  abstract  void SendMessage(int stateChange);


    public abstract void StartCoffee();

    public abstract void StopTV();

    public abstract void UpCurtains();
}
