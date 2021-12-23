package observer.improve;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class Client {
    public static void main(String[] args) {

        //创建一个WeatherData
        WeatherData weatherData = new WeatherData();
        //创建观察者
        CurrentConditions currentConditions = new CurrentConditions();
        Baidu baidu = new Baidu();
        //注册到weatherData
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(baidu);
        //测试
        System.out.println("通知");
        weatherData.setData(11,22,33);
    }
}
