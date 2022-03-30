package com.wzp.test.spring注解驱动.spring_annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author
 * @date 2022 年 03 月 29 日
 */
@Aspect //声明是一个切面类
public class LogAspects {
    //抽取公共的切入点表达式
    //1.本类引用
    //2。其他的切面引用，使用方法全名
    @Pointcut("execution(public int com.wzp.test.spring注解驱动.spring_annotation.aop.MathCalculator.*(..))")
    public  void pointCut(){

    }
    @Before("pointCut()")
    public void logStart(){
        System.out.println("除法运行 。。。参数列表。。。");
    }
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object e: args) {
            System.out.println(e);

        }
        System.out.println("除法" + "结束。。。"+"执行这：===》"+joinPoint.getSignature().getName());
    }
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("除法正常返回" +"返回值"+result);
    }
    //joinPoint 一定要出现在参数表的第一位
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        System.out.println("除法异常。"+joinPoint.getSignature().getName()+"。。异常信息：{}"+exception);
    }
}
