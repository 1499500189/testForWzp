package io.renren.modules.dynamic_object;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author
 * @date 2021 年 11 月 12 日
 */
@AllArgsConstructor
@Data
public class TableHeadVO {

    //传值
    private String  column_name;
    //显示的
    private  String column_comment;
}
