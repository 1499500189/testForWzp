package test;



/**
 * @author
 * @date 2021 年 11 月 12 日
 */

public class DictionariesVO {

    //传值
    private String  value;
    //显示的
    private  String label;

    public DictionariesVO(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
