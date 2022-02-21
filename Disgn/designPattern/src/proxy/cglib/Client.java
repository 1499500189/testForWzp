package proxy.cglib;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
public class Client {
    public static void main(String[] args) {
 //创建目标对象
        TeacherDao teacherDao = new TeacherDao();
        //获取到代理对象 ，并且将目标对象传递给代理对象
       // TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(teacherDao).getProxyInstance();
        //执行代理对象的方法，触发intercept方法
     //   String teach = proxyInstance.teach();
       // System.out.println(teach);

        System.out.println();
    }
}
