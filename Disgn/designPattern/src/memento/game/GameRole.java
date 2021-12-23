package memento.game;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class GameRole {
    private  int vit;
    private  int def;

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    //创建Memento ，即根据游戏角色的当前的状态得到Memento
    public  Memento createMemento(){
        return  new Memento(vit,def);
    }
    //从备忘录对象，恢复GameRole的状态
    public  void  recoverGameRoleFromMemento(Memento memento){
        this.def = memento.getDef();
        this.vit = memento.getVit();
    }
    //显示当前游戏角色的状态
     public  void  display(){
         System.out.println("游戏当前角色的攻击力："+ this.vit +"防御力："+this.def);
     }
}
