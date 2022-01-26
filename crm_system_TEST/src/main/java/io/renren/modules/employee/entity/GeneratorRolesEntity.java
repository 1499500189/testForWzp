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
@TableName("generator_roles")
public class GeneratorRolesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 职位 ，也是用户的角色，硬性编码用于数字字典
	 */
	@TableId
	private Integer rolesId;
	/**
	 * 职位的名字
	 */
	private String rolesName;
	/**
	 * 职位的int值，1部为01，2部为02
	 */
	private String rolesInt;

}
