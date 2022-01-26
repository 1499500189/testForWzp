package io.renren.modules.generator.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author
 * @date 2022 年 01 月 25 日
 */
@Data
public class WorkbenchProjectVo {
    /**
     * 项目id
     */
    @TableId
    private Long projectId;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 绩效数量
     */
    private Double numberAchievements;

    //积分
    private  Integer totalPoints;

    //是不是积分
    private  boolean  ifIntegral;

}
