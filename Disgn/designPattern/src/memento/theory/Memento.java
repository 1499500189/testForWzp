package memento.theory;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class Memento {
    private  String state;
    //构造器
    public Memento(String state) {
        this.state =state;
    }
    public  String  getState(){
        return  state;
    }
}
