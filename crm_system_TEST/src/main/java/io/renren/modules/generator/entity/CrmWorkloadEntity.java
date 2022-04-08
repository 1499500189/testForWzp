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
 * @date 2022-01-24 14:20:25
 */
@Data
@TableName("crm_workload")
public class CrmWorkloadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工作量汇报表的id
	 */
	
	@TableId
	private Long id;
	/**
	 * 员工id
	 */
	private Integer employeeId;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 操作日期
	 */
	@DateTimeFormat("yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@ExcelProperty("日期")
	private Date operationDate;
	/**
	 * 呼出量
	 */
	private Integer exhaledVolume;
	/**
	 * 接通量
	 */
	private Integer onCapacity;
	/**
	 * 用户的id
	 */
	private Long userId;

	//备注
	private  String remarks;


	//用户的名字
	@TableField(exist = false)
	private  String username;


}
