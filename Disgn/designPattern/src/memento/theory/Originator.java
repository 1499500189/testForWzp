package memento.theory;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class Originator {
    private  String state ;  //状态信息
    public  String getState(){
        return  state;
    }
    public  void  setState(String state){
        this.state =state;
    }
    //编写一个方法 ，可以保存一个状态对象 Memento
    //因此编写一个方法，返回Memento
    public  Memento saveStateMemento(){
        return  new Memento(state);
    }
    public  void getStateFromMemento(Memento memento){
        state  = memento.getState();
    }
}
