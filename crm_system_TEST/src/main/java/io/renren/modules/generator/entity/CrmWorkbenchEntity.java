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
@Data
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
	private String categoryName ;
	/**
	 * 降后金额
	 */
	private Double reducedAmount;
	/**
	 * 套餐金额
	 */
	private Double packageAmount;
	/**
	 * 直降金额
	 */
	private Double downAmount;
	/**
	 * 积分总数
	 */
	private Double totalPoints;
	/**
	 * 业绩数
	 */
	private Double numberAchievements;
	/**
	 * 所属人
	 */
	private Long userId;
	/**
	 * 手机号
	 */
	private String telephone;
	/**
	 * 是否有效
	 */
	private String isTrue;

	/**
	 * 所属人的名字
	 */
	@TableField(exist = false)
	private String username;
	//工号
	private String jobNumber;

	//备注
	private String remarks;



}
