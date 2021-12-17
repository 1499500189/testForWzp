package template;

import com.sun.deploy.cache.AbstractLocalApplicationProperties;

/**
 * @author
 * @date 2021 年 12 月 16 日
 */
public class Client {
    public static void main(String[] args) {
      //制作红豆豆浆
        System.out.println("制作红豆都将");
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        //制造另一种豆浆
        SoyaMilk  soyaMilk = new PeanuSoyaMilk();
        soyaMilk.make();

        //制作纯豆浆
        SoyaMilk  pureSoyas  = new PureSoysMilk();
        pureSoyas.make();
    }
}
