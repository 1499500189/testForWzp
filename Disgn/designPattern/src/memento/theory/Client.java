package memento.theory;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class Client {
    //创建一个原生的对象
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("状态1  攻击力100 ");
        caretaker.add(originator.saveStateMemento());
        originator.setState("状态2  攻击力变成了  80");
        caretaker.add(originator.saveStateMemento());
        originator.setState("状态3  攻击力变成了50");
        caretaker.add(originator.saveStateMemento());
        System.out.println("当前的状态是="+originator.getState());
        System.out.println("当前的状态是="+originator.getState());
        //希望得到状态1,将originator的状态恢复到状态1
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("当前的状态是="+originator.getState());
    }
}
