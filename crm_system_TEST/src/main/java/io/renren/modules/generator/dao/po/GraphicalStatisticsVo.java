package io.renren.modules.generator.dao.po;


import lombok.Data;

/**
 * @author
 * @date 2022 年 01 月 24 日
 */
@Data
public class GraphicalStatisticsVo {

    private  String projectName;//项目名称
    private  Long   projectId; //项目id
    private  Double numberAchievements ;//业绩数
    private  String username ; //用户名字
    private  Integer count ;  //总计

}
