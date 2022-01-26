package io.renren.modules.employee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-02 10:01:39
 */
@Data
@TableName("generator_department")
public class GeneratorDepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门的id
	 */
	@TableId
	private Integer departmentId;
	/**
	 * 部门的姓名
	 */
	private String departmentName;
	/**
	 * 部门的int值 ，一部为01
	 */
	private String departmentInt;

}
