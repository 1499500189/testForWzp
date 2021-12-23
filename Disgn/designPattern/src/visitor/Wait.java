package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public class Wait extends  Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评鉴是待定");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价是待定");
    }
}
