package io.renren.modules.generator.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 15:00:52
 */
@TableName("crm_workbench")
public class CrmWorkbenchEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 日期
	 */
	//ali excel 的注解 ，解决格式问题，不带时分秒
	@DateTimeFormat("yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@ExcelProperty("日期")
	private Date processingDate;
	/**
	 * 记录查看日期
	 */
	private Date createTime;
	/**
	 * 关联crm_project表，项目名称id
	 */
	private Long projectId;
	/**
	 * 项目名称的名字
	 */
	@ExcelProperty("项目名称")
	@TableField(exist = false)
	private String projectName ;

	/**
	 * 项目类别的id
	 */
	private Long categoryId;
	/**
	 * 项目类别的名字
	 */
	@TableField(exist = false)
	@ExcelProperty("项目类别")
	private String categoryName ;
	/**
	 * 降后金额
	 */
	@ExcelProperty("降后金额")
	private Double reducedAmount;
	/**
	 * 套餐金额
	 */
	@ExcelProperty("套餐金额")
	private Double packageAmount;
	/**
	 * 直降金额
	 */
	@ExcelProperty("直降金额")
	private Double downAmount;
	/**
	 * 积分总数
	 */
	@ExcelProperty("积分总数")
	private Double totalPoints;
	/**
	 * 业绩数
	 */
	@ExcelProperty("业绩数")
	private Double numberAchievements;
	/**
	 * 所属人
	 */
	private Long userId;
	/**
	 * 手机号
	 */
	/**
	 * 手机号
	 */
	@ExcelProperty("客户手机号")
	private String telephone;
	/**
	 * 是否有效
	 */
	@ExcelProperty("是否有效")
	private String isTrue;

	/**
	 * 所属人的名字
	 */
	@TableField(exist = false)
	@ExcelProperty("所属人")
	private String username;
	//工号
	@ExcelProperty("工号")
	private String jobNumber;

	//备注
	private String remarks;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
