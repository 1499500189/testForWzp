package memento.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */

//守护者对象,保存游戏角色的状态
public class Caretaker {
    //如果只要保存一次的状态  ,当前使用这个的一种
    private  Memento memento;
    //对GameRole 保存多次状态
    private ArrayList<Memento> mementos;
    //对多个游戏角色保存多个状态
    private HashMap<String,ArrayList<Memento>> rolesMementos;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
