package proxy.staticprooxy;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
public class Client {


    public static void main(String[] args) {


        //创建目标的对象(被代理的对象)
        TeacherDao teacherDao = new TeacherDao();
        //创建一个代理对象，同时将被代理对象传递给代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);
        //通过代理对象调用被代理对象的方法  调用teach方法
        //执行的是代理对象的方法，代理对象再去调用目标对象的方法
        teacherDaoProxy.teach();

    }
}
