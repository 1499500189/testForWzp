package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public class Man  extends  Person{

    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
