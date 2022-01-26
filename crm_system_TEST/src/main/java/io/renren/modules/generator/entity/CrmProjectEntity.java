package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 11:48:12
 */
@Data
@TableName("crm_project")
public class CrmProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * 排序规则
	 */
	private Integer sortIndex;
	/**
	 * 项目类别
	 */
	@TableField(exist = false)
	private String category;
	/**
	 * 绩效数量
	 */
	private Double achievements;
	/**
	 * 项目类别的id
	 */
	private  Long categoryId;


}
