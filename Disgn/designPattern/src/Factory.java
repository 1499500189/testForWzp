import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public class Factory {
    public static void main(String[] args) {
        Calendar  cal =Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.DATE));
        Date date = new Date();
        int year = date.getYear();
        System.out.println(year);
        long time = date.getTime();
        System.out.println(time);
        //谁说五uu大大多好都会受到啥      大黄蜂护肤水水水水水水dosage


    }
}
