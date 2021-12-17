package proxy.staticprooxy;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
//代理对象，静态的代理
public class TeacherDaoProxy  implements  ITeacherDao{
    private  ITeacherDao target ; //目标对象，通过接口进行依赖

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理");
        target.teach();
        System.out.println("进行教师的增强方法");
        System.out.println("代理结束");
    }
}
