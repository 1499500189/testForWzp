package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public class Success extends  Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价该歌手狠成功！");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人的评鉴该歌手很成功");
    }
}
