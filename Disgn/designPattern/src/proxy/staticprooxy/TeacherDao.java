package proxy.staticprooxy;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
public class TeacherDao implements  ITeacherDao{
    @Override
    public void teach() {
        System.out.println("子类的重写的方法 ，授课");
    }
}
