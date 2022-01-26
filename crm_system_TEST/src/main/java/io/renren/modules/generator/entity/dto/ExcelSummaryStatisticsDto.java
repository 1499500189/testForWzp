package io.renren.modules.generator.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author
 * @date 2022 年 01 月 26 日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExcelSummaryStatisticsDto {

    /**
     * 日期
     */
    //ali excel 的注解 ，解决格式问题，不带时分秒

//操作日期
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ExcelProperty("日期")
    private Date operationDate;

    //名字
    @ExcelProperty("所属人")
    private  String username  ;
    //呼出量
    @ExcelProperty("呼出量")
    private  Long exhaledVolume;
    //接通量
    @ExcelProperty("接通量")
    private  Long onCapacity;
    //业绩数
    @ExcelProperty("业绩数")
    private  Long numberAchievements;
    private  Long Id;
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getExhaledVolume() {
        return exhaledVolume;
    }

    public void setExhaledVolume(Long exhaledVolume) {
        this.exhaledVolume = exhaledVolume;
    }

    public Long getOnCapacity() {
        return onCapacity;
    }

    public void setOnCapacity(Long onCapacity) {
        this.onCapacity = onCapacity;
    }

    public Long getNumberAchievements() {
        return numberAchievements;
    }

    public void setNumberAchievements(Long numberAchievements) {
        this.numberAchievements = numberAchievements;
    }
}
