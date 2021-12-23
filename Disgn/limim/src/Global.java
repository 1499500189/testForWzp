import java.lang.reflect.Field;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
public class Global {

    public static final String YES = "1";

    private static final String NO ="0";
    private  static   String NO1 ="2";

    public static Object getConst(String field){

        try {

            return Global.class.getField(field).get(null);

        } catch (Exception e) {

        }

        return null;

    }

    public static Object getPrivateConst(String field){

        try {

            Field f = Global.class.getDeclaredField(field);

            f.setAccessible(true);

            return f.get(null);

        } catch (Exception e) {}

        return null;

    }

}
