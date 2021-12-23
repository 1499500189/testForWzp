package memento.game;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class Client {
    public static void main(String[] args) {

        GameRole gameRole = new GameRole();
        gameRole.setDef(100);
        gameRole.setVit(100);
        System.out.println("大战之前   ，  和boss大战之前的状态");
        gameRole.display();
        Caretaker caretaker = new Caretaker();
        //把当前的状态保存caretaker
        caretaker.setMemento(gameRole.createMemento());
        System.out.println("和boss大战");
        gameRole.setVit(30);
        gameRole.setDef(30);
        gameRole.display();
        //大战之后使用备忘录对象恢复到之前的状态
        System.out.println("大战之后使用备忘录对象恢复到之前的状态");
        gameRole.recoverGameRoleFromMemento(caretaker.getMemento());
        System.out.println("恢复之后的状态");
        gameRole.display();
    }
}
