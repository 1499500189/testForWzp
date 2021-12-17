package proxy.dynamic;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
public class TeacherDao implements  ITeacherDao{
    @Override
    public void teacher() {
        System.out.println("重写方法 ， 老师授课");
    }
}
