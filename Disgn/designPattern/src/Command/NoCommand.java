package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
//没有任何命令 ，空实现 ：用于初始化每个按钮 ，当调用空命令时，对象什么都不做
    //其实，这样时一种设计模式，可以省掉对空的判断
public class NoCommand implements command{
    @Override
    public void execute() {
    }

    @Override
    public void undo() {

    }
}
