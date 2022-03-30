package com.wzp.test.spring注解驱动.spring_annotation.config;

import com.wzp.test.spring注解驱动.spring_annotation.service.Person;
import com.wzp.test.spring注解驱动.spring_annotation.condition.LinuxCondition;
import com.wzp.test.spring注解驱动.spring_annotation.condition.WindowsCondition;
import com.wzp.test.spring注解驱动.spring_annotation.entity.Color;
import com.wzp.test.spring注解驱动.spring_annotation.entity.ColorFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Conditional;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
/*@Conditional({LinuxCondition.class})*/
@Configuration
@Conditional({LinuxCondition.class})
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
//导入组件 ， id默认是组件的全类名
public class MainConfig2 {
    //默认是单例的 ，  通过
    /*
    *prototype  多实例 ， ioc启动并不会去调用方法创建对象放在容器中 。
    * singleton：单实例 ， ioc容器启动会调用方法创建对象放到ioc容器中
    *
    *
    *
    * 懒加载 ：
    *    懒加载针对单实例
    *    单实例bean：默认在容器启动的时候创建对象
    *    懒加载：默认启动的时候不创建  ，延迟创建  ，第一次使用（获取）的bean的时候创建对象，并且初始化
    *
    * */
  //  @Scope("prototype")
    @Lazy
    @Bean("person1")
    public Person  person12(){
        return new Person("pop",2);
    }
    /*
    *  @Conditional 按照一定条件进行判断， 满足条件给容器中注册bean
    * */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("bill" ,99);
    }
    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person person02(){
        return new Person("linux" ,77);
    }
    /*
    * 给容器中注册组件：
    * 1)、包扫描，+组件标注注解（@Controller 。。）
    * 2）、@Bean{导入第三方的组件}
    * 3）、@Import 快速的给容器中导入一个组件
    *         1）@Import （要导入到容器中的组件）；容器中就会自动注册这个组件， id默认是全类名
    *         2）@ImportSelector：返回需要导入的组件的全类名数组
    *         3) @ImportBeanDefinitionRegistrar  手动注册bean到容器中
    * 4）、使用spring提供的FactoryBean（工厂Bean）
    *       1)默认获取到的是工厂bean调用getObject创建的对象
    *       2）要默认获取工厂bean本身，我们需要个id前面加一个&
    *         &colorFactoryBean
    *
    * */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return  new ColorFactoryBean();
    }

}
