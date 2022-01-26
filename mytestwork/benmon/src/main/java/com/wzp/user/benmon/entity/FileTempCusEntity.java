package com.wzp.user.benmon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-09 13:59:55
 */
@Data
@TableName("crm_temp_cus")
public class FileTempCusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户的临时表的id
	 */
	@TableId(type = IdType.INPUT)
	private Integer tempId;
	/**
	 * 客户的手机号
	 */
	private String telephone;
	/**
	 * 是否已经分配到小组
	 */
	private Integer groupsId;
	/**
	 * 是否已经分配到部门的id，关联表
	 */
	private Integer departmentId;
	/**
	 * 分配到个人的id时 ，生成客户表
	 */
	private Long currentTrackingId;
	/**
	 * 是否完成数据的携号转网判断 ，0未完成本文件中的判断，1完成，使用异步来完成
	 *     0标识该条数据不是携号转网的数据， 1表示是携号转网的数据 。
	 */
	private Integer isNcnt;

	private    Long fileId;
	private   String operator;

}
