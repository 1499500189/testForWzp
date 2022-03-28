package com.wzp.test.spring注解驱动.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
//自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {
    @Override
    //返回值，就是到导入到容器中的组件全类名
    //AnnotationMetadata:当前标注@Import注解的类的所有注解信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MergedAnnotations annotations = importingClassMetadata.getAnnotations();
        System.out.println(annotations);

        return new String[]{
            "com.wzp.test.spring注解驱动.entity.Red"};
    }
}
