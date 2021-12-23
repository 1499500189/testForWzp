package observer.improve;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class Baidu  implements  Observer{

    //温度 ，气压 ，湿度
    private  float temperature ;
    private  float pressure;
    private  float humidity;

    public  void update(float temperature,float pressure ,float humidity ){
        this.humidity=humidity;
        this.pressure=pressure;
        this.temperature=temperature;
        System.out.println("百度   sss"
        );
        display();
    }

    private void display() {
        System.out.println(temperature);
        System.out.println("** pr"+pressure);
        System.out.println("***today mHumidity"+humidity);
    }

}
