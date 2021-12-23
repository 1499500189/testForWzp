package observer;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class WeatherData {
    //核心类
    //包含最新的天气情况信息
    //包含currentConditions对象
    //当数据有更新的时候，就主动的调用 currentConditions对象update方法(含display) ，这样他们(接入方)就看到最新的信息
    private float temperatrue;
    private float pressure;
    private float humidity;
    private CurrentConditions currentConditions;

    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    public float getTemperatrue() {
        return temperatrue;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void dataChange() {
        currentConditions.update(getTemperatrue(), getPressure(), getHumidity());
    }

    public void setData(float temperatrue, float pressure, float humidity) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperatrue = temperatrue;
        //调用dataChange，将最新的信息，推送给 接入方 currentConditions
        dataChange();
    }
}
