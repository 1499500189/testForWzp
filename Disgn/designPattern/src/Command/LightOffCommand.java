package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
//电灯关闭
public class LightOffCommand implements command{
      LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    //聚合lightReceiver的对象
    @Override
    public void execute() {
        //调用接收者的方法
        light.off();
    }

    @Override
    public void undo() {
        //调用接收者的方法
        light.on();

    }
}
