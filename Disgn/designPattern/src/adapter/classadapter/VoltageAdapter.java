package adapter.classadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
//适配类
public class VoltageAdapter extends  Voltage220v implements IVoltage5v {
    @Override
    public int output5v() {
        int srcV =output220();
        int dstV = srcV/44;  //转陈5v
        return dstV;
    }
}
