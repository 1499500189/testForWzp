package observer;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class Client {
    //创建接入方currentConditions
    public static void main(String[] args) {
        CurrentConditions currentConditions = new CurrentConditions();
        WeatherData weatherData = new WeatherData(currentConditions);
        weatherData.setData(11,11,12);
        weatherData.setData(22,22,22);
    }


}
