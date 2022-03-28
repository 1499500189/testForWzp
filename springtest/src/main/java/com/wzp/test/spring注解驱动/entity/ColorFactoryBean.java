package com.wzp.test.spring注解驱动.entity;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
//创建一个spring定义的factoryBean
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    //返回一个Color对象 ，这个对象会添加到容器中
    public Color getObject() throws Exception {
        return new Color();
    }

     //是否是单例的 ，
     //true ：这个bean是单实例， 在容器中保存一份
     //false ：多实例
    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }
}
