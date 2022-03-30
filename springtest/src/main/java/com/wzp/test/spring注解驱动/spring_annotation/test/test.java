package com.wzp.test.spring注解驱动.spring_annotation.test;

import com.wzp.test.spring注解驱动.spring_annotation.service.Person;
import com.wzp.test.spring注解驱动.spring_annotation.config.MainConfig;
import com.wzp.test.spring注解驱动.spring_annotation.config.MainConfig2;
import com.wzp.test.spring注解驱动.spring_annotation.config.MainConfigOfLifeCycle;
import com.wzp.test.spring注解驱动.spring_annotation.entity.Person2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class test {
  @Test
    public void test2(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    System.out.println("===========================");
    for (String s : beanDefinitionNames) {
      System.out.println(s);
    }
    System.out.println("===========================");
    //context.getBeanNamesForType()
  }
  @Test
  public void test21(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    System.out.println("===========================");
    for (String s : beanDefinitionNames) {
      System.out.println(s);
    }
    System.out.println("===========================");
    //工厂bean获取的是调用getObject创建的对象
    Object colorFactoryBean = context.getBean("colorFactoryBean");
    System.out.println(colorFactoryBean);
    Object colorFactory = context.getBean("&colorFactoryBean");
    System.out.println(colorFactory);
    //context.getBeanNamesForType()
  }
  @Test
  public void test3(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
    Object person = context.getBean("person1");
    Object person1 = context.getBean("person1");
    System.out.println(person==person1);
  }
  @Test
  public void test4() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
    Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
    System.out.println(beansOfType);
  }
  @Test
  public void test5() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
    System.out.println("容器创建完成。。。。");
     context.close();
  }
  @Test
  public void test6() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
    Person2 person = (Person2) context.getBean("person");
    System.out.println("======>"+person);
    System.out.println(person.getAge());
    System.out.println(person.getName());

    ConfigurableEnvironment environment = context.getEnvironment();
    //获取到配置文件中的k/v
    String property = environment.getProperty("person.nickname");
  }
}
