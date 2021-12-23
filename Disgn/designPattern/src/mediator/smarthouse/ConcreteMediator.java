package mediator.smarthouse;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
public class ConcreteMediator extends  Mediator{
    //集合 ，存放所有的同事对象
    private HashMap<String,Colleague> colleagueMap;
    private  HashMap<String,String> interMap;

    public ConcreteMediator() {
        colleagueMap =new HashMap<>();
        interMap =new HashMap<>();
    }


    @Override
    public void Register(String colleagueName, Colleague colleague) {
        colleagueMap.put(colleagueName,colleague);
        if (colleague instanceof  Alarm){
            interMap.put("Alarm",colleagueName);
        }else if (colleague instanceof CoffeeMachine){
            interMap.put("CoffeeMachine",colleagueName);
        }else if (colleague instanceof TV){
            interMap.put("TV",colleagueName);
        }else if (colleague instanceof Curtains){
            interMap.put("Curtains",colleagueName);
        }
    }
    //具体的中介者的核心方法
    //1.根据得到的消息，完成对应任务
    //2，中介者在这个方法，协调各个具体的同事对象，完成任务
    @Override
    public void GetMessage(int stateChange, String colleagueName) {

         if (colleagueMap.get(colleagueName) instanceof  Alarm){
             if (stateChange==0){
                 ((CoffeeMachine)(colleagueMap.get(interMap.get("CoffeeMachine")))).StartCoffee();
                 ((TV)(colleagueMap.get(interMap.get("TV")))).StartCoffee();
             }else if (stateChange==1){
                 ((TV)(colleagueMap.get(interMap.get("TV")))).StopTV();
             }
         }else if (colleagueMap.get(colleagueName) instanceof  CoffeeMachine){
             ((Curtains)(colleagueMap.get(interMap.get("Curtains")))).UpCurtains();
         }else if (colleagueMap.get(colleagueName) instanceof  TV){
             System.out.println("不处理");
         }else if (colleagueMap.get(colleagueName) instanceof  Curtains){
         //处理的是窗帘的消息
         }
    }

    @Override
    public void SendMessage() {

    }
}
