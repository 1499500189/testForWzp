package flyweight;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 12 月 14 日
 */

//网站工厂类 ，根据需要返回压一个网站
public class WebSiteFactory {
    //集合，充当池得作用
    private HashMap<String,ConcreteWebSite> pool =new HashMap<>();
    //根据网站的类型，返回一个网站，如果没有就创建一个网站，并放入到池中，并返回
    public  WebSite getWebSiteCategory(String type){
        if (!pool.containsKey(type)){
            pool.put(type,new ConcreteWebSite(type));
        }
        return      (WebSite)pool.get(type);
    }
    //获取网站分类的总数（池中哦有多少个网站类型）
    public int getWebSiteCount(){
        return  pool.size();
    }
}
