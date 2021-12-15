package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Client {
    public static void main(String[] args) {
        //电一份 LongBlack(
        Drink longBlack = new LongBlack();
        System.out.println("费用1="+longBlack.cost());
        System.out.println("描述是"+longBlack.getDes());

        //之后加入一份牛奶
         longBlack = new Milk(longBlack);
        System.out.println("加入一份牛奶"+longBlack.cost());
        System.out.println("加入一份牛奶的描述"+longBlack.getDes());
    }
}
