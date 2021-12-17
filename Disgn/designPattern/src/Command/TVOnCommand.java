package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
public class TVOnCommand implements command{
    //聚合tvreceiver
    TVReceiver tv;

    public TVOnCommand(TVReceiver tv) {

        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
          tv.off();
    }
}
