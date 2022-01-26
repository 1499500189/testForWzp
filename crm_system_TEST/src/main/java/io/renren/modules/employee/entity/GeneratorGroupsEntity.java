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
@TableName("generator_groups")
public class GeneratorGroupsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 小组的组名的id
	 */
	@TableId
	private Integer groupsId;
	/**
	 * 小组的组名
	 */
	private String groupsName;
	/**
	 * 小组的组int值，一部为01，这种的
	 */
	private String groupsInt;

}
