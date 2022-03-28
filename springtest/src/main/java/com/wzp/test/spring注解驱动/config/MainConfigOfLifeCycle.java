package com.wzp.test.spring注解驱动.config;

import com.wzp.test.spring注解驱动.entity.Car;
import com.wzp.test.spring注解驱动.entity.Person2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * bean的生命周期：
 *          bean创建---初始化----销毁的过程
 *          容器管理bean 的生命周期
 *          我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 *构造（对象创建）
 *  单实例：在容器启动的时候创建对象
 *  多实例：在每次获取的时候创建，每次不同
 *          1）、指定初始化和销毁方法：
 *          指定init-method 和destroy-method
 *            初始化：对象创建完成，并赋值完成，调用初始化方法
 *            销毁：容器关闭的时候
 *            如果scope是多实例的时候，在获取bean的时候调用，
 *                单实例：容器关闭的时候
 *                多实例：容器不会管理这个bean，容器不会销毁，需要手动销毁
 *          2）通过让bean实现InitializingBean接口（定义初始化逻辑），DisposableBean（定义销毁逻辑）
 *          3)使用JSR250规范里的两个注解
 *             @PostConstruct；在bean创建完成并且属性赋值完成 ；来执行初始化方法
 *             @PreDestroy：在容器销毁之前调用
 *          4)BeanPostProcessor{interface};bean的后置处理器；
 *            在bean初始化前后进行一些处理工作
 *            postProcessBeforeInitialization:在初始化之前工作
 *            postProcessAfterInitialization：在初始化之后工作
 *
 *           遍历得到容器中所有的BeanPostProcessor ；挨个执行beforeInitialization
 *           一但返回null。跳出for循环，不会执行后面的BeanPostProcessor
 *
 *            在spring启动过程中bean执行顺序
 *                             populateBean 进行数据初始化，数据赋值
 *
 *                             applyBeanPostProcessorsBeforeInitialization
 *                             invokeInitMethods执行初始化
 *                             applyBeanPostProcessorsAfterInitialization
 *
 *       Spring底层对BeanPostProcessor的使用：
 *             bean赋值，注入其他组件 @Autowired，生命周期注解功能，@Async，xxx
 *
 *
 *
 ** @使用@Value赋值：
 *  *  1.基本数值
 *  *  2.可以写SpEL；#{}
 *  *  3.可以写${}；取出配置文件中的值（在循行环境变量里面的值）
*
 *
 *    @Value("张三")
 *     private  String name;
 *     @Value("${配置文件值}")
 *     private Integer age;
 *
 *
 */
//@PropertySource读取外部配置文件中的k/v保存到运行环境中
@PropertySource(value = {"classpath:/person.properties"})
@ComponentScan("com.wzp")
@Configuration
public class MainConfigOfLifeCycle {

    /*@Bean(initMethod = "init",destroyMethod = "destroy")*/
    @Bean
    public Car car(){
        return new Car();
    }
     //使用Value注解配置属性
    //读取@PropertySource读取外部配置文件， k/v 保存到运行环境变量中；加载完外部的配置文件以后使用${}取出配置文件的值

    @Bean("person")
    public Person2 person(){
        return new Person2("sd",22);
    }
}
