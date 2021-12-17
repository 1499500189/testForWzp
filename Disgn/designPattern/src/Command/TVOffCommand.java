package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
public class TVOffCommand implements command{
    TVReceiver tv;

    public TVOffCommand(TVReceiver tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}
