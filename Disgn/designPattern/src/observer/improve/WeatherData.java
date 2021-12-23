package observer.improve;

import observer.CurrentConditions;

import java.util.ArrayList;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class WeatherData  implements  Subject{
    //核心类
    //包含最新的天气情况信息
    //包含观察者集合，使用ArrayList管理
    //当数据有更新的时候，就主动的调用 ArrayList对象update方法(含display) ，这样他们(接入方)就看到最新的信息
     private float temperatrue;
    private float pressure;
    private float humidity;
    //观察者集合
     private ArrayList<Observer> observers;
    public WeatherData() {
        observers =new ArrayList<>();

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

        notifyObservers();
 /*       currentConditions.update(getTemperatrue(), getPressure(), getHumidity());*/
    }

    public void setData(float temperatrue, float pressure, float humidity) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperatrue = temperatrue;
        //调用dataChange，将最新的信息，推送给 接入方 currentConditions
        dataChange();
    }

    @Override
    public void registerObserver(Observer o) {
            observers.add(o);  //注册一个观察者
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)){
            observers.remove(o); //移除一个观察者
        }
    }

    @Override
    public void notifyObservers() {
     //遍历所有的观察者，并通知
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(this.temperatrue,this.pressure,this.humidity);
            // 调用update方法
        }
    }
}
