package io.renren.modules.generator.entity.dto;

import cn.hutool.core.annotation.Alias;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @date 2022 年 01 月 21 日
 */
@Data
@ToString
public class ExcelWorkbench implements Serializable {

    private String processingDate;
    /**
     * 手机号
     */
    @Alias("客户手机号")
    private String telephone;

    /**
     * 项目名称的名字
     */
    @Alias("项目名称")
    private String projectName ;

    /**
     * 项目类别的名字
     */
    @Alias("项目类别")
    private String categoryName ;
    /**
     * 降后金额
     */
    @Alias("降后金额")
    private Double reducedAmount;
    /**
     * 套餐金额
     */
    @Alias("套餐金额")
    private Double packageAmount;
    /**
     * 直降金额
     */
    @Alias("直降金额")
    private Double downAmount;
    /**
     * 积分总数
     */
    @Alias("积分总数")
    private Double totalPoints;
    /**
     * 业绩数
     */
    @Alias("业绩数")
    private Double numberAchievements;


    /**
     * 是否有效
     */
    @Alias("是否有效")
    private String isTrue;

    /**
     * 所属人的名字
     */
    @Alias("所属人")
    private String username;
    //工号
    @Alias("工号")
    private String jobNumber;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getReducedAmount() {
        return reducedAmount;
    }

    public void setReducedAmount(Double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }

    public Double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public Double getDownAmount() {
        return downAmount;
    }

    public void setDownAmount(Double downAmount) {
        this.downAmount = downAmount;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Double getNumberAchievements() {
        return numberAchievements;
    }

    public void setNumberAchievements(Double numberAchievements) {
        this.numberAchievements = numberAchievements;
    }

    public String getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

}
