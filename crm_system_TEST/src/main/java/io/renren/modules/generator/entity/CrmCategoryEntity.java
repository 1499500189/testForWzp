package io.renren.modules.generator.entity;

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
 * @date 2022-01-21 13:42:08
 */
@Data
@TableName("crm_category")
public class CrmCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 项目类型的id
	 */
	@TableId
	private Long categoryId;
	/**
	 * 项目类型的名称
	 */
	private String categoryName;
	/**
	 * 项目排序
	 */
	private Integer categorySort;

}
