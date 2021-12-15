package decorator;

import java.io.InputStream;
import java.util.concurrent.Executors;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Espresso  extends  Coffee{
    public Espresso(){
        setDes("意大利咖啡");
        setPrice(0.07f);
    }
}
