package template;

/**
 * @author
 * @date 2021 年 12 月 16 日
 */
public class PureSoysMilk extends  SoyaMilk{
    @Override
    void addCondiments() {
    }//空实现

    @Override//重写钩子方法
    boolean customerWantCondiments() {
        return false;
    }
}
