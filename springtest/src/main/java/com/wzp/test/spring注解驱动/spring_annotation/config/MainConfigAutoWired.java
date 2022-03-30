package com.wzp.test.spring注解驱动.spring_annotation.config;

import com.wzp.test.spring注解驱动.spring_annotation.dao.BookDao;
import com.wzp.test.spring注解驱动.spring_annotation.entity.Car;
import com.wzp.test.spring注解驱动.spring_annotation.entity.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 使用@bean的好像是先获取 ，设置的会被 @value指定的覆盖
 * 自动装配 ；
 *    spring 利用依赖注入（DI） 完成对ioc容器中中各个组件的依赖关系赋值
 *    1)Autowired:自动注入
 *         1)默认优先按照类型去容器中找对应的组件
 *         2)如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *            applicationContext.getBean(”bookDao“)
 *         3)@Qualifier("bookDao2") 使用@Qualifier 注解指定需要装配的组件的id，而不是使用属性名
 *         4）自动装配默认一定要将属性赋值好， 没有就会报错
 *            使用autowired中 required=false  能装配装配，不能就不装配
 *         5）@Primary注解让spring进行自动装配时 默认使用首选的bean
 *              也可以继续使用@Qualifier指定需要装配的bean的名字
 *     2)spring 还支持使用@Resource（JSR250）和@Inject（JSR330）[java规范]
 *              @Resource：
 *                  可以和@Autowired一样实现自动装配功能， 默认是属性的名称
 *                   可以使用 @Resource（name=”bookDao“）指定
 *                   不支持@Primary 功能 不支持 required=false
 *              @Inject:
 *                 需要导入javax。inject的包和Autowired的功能一样 ，
 *      3）@Autowired:构造器，方法，参数，属性位置都能用，
 *               1） 方法上：set方法上 ， 有@bean的标注的方法创建对象的时候 方法参数的值从容器中获取 ：也会自动从容器中获取
 *               2）有参构造器上 ：如果只有一个有参构造器 ，这个有参构造器参数的@Autowired可以省略，可以自动从容器中获取
 *               3）放在参数位置@AutoWired Car car
 *      4）自定义组件想要使用spring容器底层的一些组件 （ApplicationContext，BeanFactory，xxx）；
 *          自定义组件实现xxxAware ：再创建对象的时候 ， 对调用接口规定的方法注入相关组件；Aware
 *          吧spring底层的一些组件注入到自定义的bean中：
 *
 * @date 2022 年 03 月 28 日
 */
@ComponentScan({"com.wzp.test.spring注解驱动.spring_annotation.controller", "com.wzp.test.spring注解驱动.spring_annotation.service",
        "com.wzp.test.spring注解驱动.spring_annotation.entity"})
@Configuration
public class MainConfigAutoWired {
    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setName("nn");
        System.out.println("设置为nn");
        return bookDao;
    }
    @Bean("color")
    public Color color(Car car){
        Color color = new Color();
        return color;
    }
}
