package template;

/**
 * @author
 * @date 2021 年 12 月 16 日
 */
public class RedBeanSoyaMilk extends  SoyaMilk{
    @Override
    void addCondiments() {
        System.out.println("添加的过程进行修改 red====");
    }
}
