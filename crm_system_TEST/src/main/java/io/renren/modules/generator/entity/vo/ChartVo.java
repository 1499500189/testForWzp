package io.renren.modules.generator.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author
 * @date 2022 年 01 月 24 日
 */
@Data
public class ChartVo {
private  String username  ;  //名字
    private  Long exhaledVolume;//呼出量
    private  Long onCapacity; //接通量
    private Date  operationDate;//操作日期
    private  Long numberAchievements;//业绩数
    private  Long Id;


}
