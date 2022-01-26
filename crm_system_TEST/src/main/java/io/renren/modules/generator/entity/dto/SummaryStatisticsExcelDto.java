package io.renren.modules.generator.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;
@Data
public class SummaryStatisticsExcelDto {

    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ExcelProperty("日期")
    private Date operationDate;//操作日期
    @ExcelProperty("所属人")
    private  String username  ;  //名字
    @ExcelProperty("呼出量")
    private  Long exhaledVolume;//呼出量
    @ExcelProperty("接通量")
    private  Long onCapacity; //接通量
    @ExcelProperty("业绩数")
    private  Long numberAchievements;//业绩数
}
