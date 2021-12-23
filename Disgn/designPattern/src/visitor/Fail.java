package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public class Fail  extends  Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("这个男人评价很失败");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("这个女人评鉴很失败");
    }
}
