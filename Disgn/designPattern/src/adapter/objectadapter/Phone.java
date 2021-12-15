package adapter.objectadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class Phone {
    //充电
    public  void charging(IVoltage5v iVoltage5v){
        if (iVoltage5v.output5v()==5){
            System.out.println("电压是五伏，可以充电");
        }else if (iVoltage5v.output5v()>5){
            System.out.println("电压为五伏，不能充电");
        }

    }
}
