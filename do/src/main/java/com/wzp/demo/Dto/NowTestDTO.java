package com.wzp.demo.Dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.util.Date;

/**
 * @author
 * @date 2021 年 11 月 08 日
 */
@Data
public class NowTestDTO {
    @ExcelProperty("姓名")
    private String  name;
    @ExcelProperty("年龄")
    private  int age ;

    @ExcelProperty("日期")
    private Date date;
}
