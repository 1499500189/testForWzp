package com.wzp.test.spring注解驱动.spring_annotation.test;

import com.wzp.test.spring注解驱动.spring_annotation.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class IOCTest_Ext {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext(ExtConfig.class);


		//�����¼���
		applicationContext.publishEvent(new ApplicationEvent(new String("�ҷ�����ʱ��")) {
		});

		applicationContext.close();
	}

}
