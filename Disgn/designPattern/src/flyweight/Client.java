package flyweight;

/**
 * @author
 * @date 2021 年 12 月 14 日
 */
public class Client {
    public static void main(String[] args) {
        //创建一个工厂类
        WebSiteFactory  factory =new WebSiteFactory();
        //客户要一个以新闻形式发布的网站
        WebSite webSite1 = factory.getWebSiteCategory("新闻");

        webSite1.user(new User("tome"));

        //创建一个博客形式的网站
        WebSite webSite2 =factory.getWebSiteCategory("博客");
        webSite2.user(new User("loii"));

        //客户仍要一个以博客形式的网站
        WebSite webSite3 =factory.getWebSiteCategory("博客");
        webSite3.user(new User("tpop"));

        //网站总共有几个
        factory.getWebSiteCount();
        System.out.println(factory.getWebSiteCount());
    }
}
