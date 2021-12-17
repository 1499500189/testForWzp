package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
//电灯打开
public class LightOnCommand implements  command{
    private  LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    //聚合lightReceiver的对象
    @Override
    public void execute() {
        //调用接收者的方法
        light.on();
    }

    @Override
    public void undo() {
        //调用接收者的方法
        light.off();

    }
}
