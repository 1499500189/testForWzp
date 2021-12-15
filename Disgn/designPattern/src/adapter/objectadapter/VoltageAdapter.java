package adapter.objectadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
//适配类
public class VoltageAdapter implements IVoltage5v {
    private Voltage220v voltage220v; //关联关系--聚合关系
    @Override
    public int output5v() {
        int dst =0;
        if (null!=voltage220v){
            int srcV =voltage220v.output220();
            System.out.println("使用对象适配器进行转换");
            dst = srcV/44;
            System.out.println("适配完成 ，输出的电压为"+dst);
        }
        return dst;
    }

    public VoltageAdapter(Voltage220v voltage220v) {
        this.voltage220v = voltage220v;
    }
}
