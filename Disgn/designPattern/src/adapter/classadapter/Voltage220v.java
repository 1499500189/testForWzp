package adapter.classadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
//被适配的类
public  class Voltage220v {
public  int output220(){
    int src =220;
    System.out.println("电压＝"+src+"伏");
    return  src;
}
}
