package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author
 * @date 2021 年 12 月 15 日
 */
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成一个代理对象
    public Object getProxyInstance() {
        //newProxyInstance 方法的三个参数
        // 1.classloader loader ：指定当前目标对象使用的类加载器，获取加载器的方法是固定的
        //2.calss<?>[] interfaces: 忙于表对象实现的接口类型，使用反射方法确认类型
        //3.InvocationHandler h ：事情处理，执行目标对象的方法时，会触发事情处理器的对象，会把当前执行的目标对象方法作为参数传入

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("jdk 代理开始");
                Object returnVal = method.invoke(target, args);
                return returnVal;
            }
        });
    }
}
