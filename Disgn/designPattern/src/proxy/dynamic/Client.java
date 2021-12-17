package proxy.dynamic;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
public class Client {
    public static void main(String[] args) {
        //创建目标对象
        ITeacherDao target = new TeacherDao();
        //给目标对象。创建代理对象
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(target).getProxyInstance();

        System.out.println("proxyInstance=" + proxyInstance);
        //他的类是一个代理对象 ，$Proxy0 ， 内存中动态生成了代理对象
        System.out.println(proxyInstance.getClass());

    }
}
