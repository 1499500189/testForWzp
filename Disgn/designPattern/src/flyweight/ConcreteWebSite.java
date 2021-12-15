package flyweight;

/**
 * @author
 * @date 2021 年 12 月 14 日
 */
public class ConcreteWebSite extends  WebSite{
 //共享的部分，内部的状态
    private  String type  =""; //网站的发布形式(类型)
//构造器
    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void user(User user) {
        System.out.println("网站的发布形式："+type+"使用中   。。。 使用者是"+user);
    }
}
