package io.renren.modules.dynamic_object;

import java.lang.annotation.*;

/**
 * @author
 * @date 2022 年 03 月 17 日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RelationFiled {

    /**
     * 视图模型对象中数据的key
     * @return
     */
    String attributeName();
    /**
     * 数据对象的属性（流程实例id）
     * @return
     */
    String propertyName() default "procInsId";
    /**
     * 数据对象要添加的字段名称
     * @return
     */
    String[] fields() default {};
}
