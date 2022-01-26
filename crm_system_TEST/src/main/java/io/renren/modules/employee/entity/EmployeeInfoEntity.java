package io.renren.modules.employee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-01 10:28:14
 */
@ApiModel(description="员工对象信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("employee_info")
public class EmployeeInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(value = "员工id")
	@TableId
	private Long id;
	/**
	 * 公司
	 */
	@ApiModelProperty(value = "员工的公司，采用默认00")
	private String company;
	/**
	 * 部门
	 */
	@ApiModelProperty(value = "部门")
	private String department;
	/**
	 * 小组
	 */
	@ApiModelProperty(value = "小组")
	private String groups;
	/**
	 * 用户的角色
	 */
	@ApiModelProperty(value = "用户角色id")
	private Long roleId;
	/**
	 * 根据公司 部门小组和具体的角色加用户名一起去构成的
	 */
	@ApiModelProperty(value = "用户的codes，后端生成")
	private String codes;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**
	 * 0删除1，未删除
	 */
	/*private Integer isDeleted;*/
	/**
	 * 父id
	 */
	@ApiModelProperty(value = "员工的父id ，后端生成")
	private Long parentId;
	/**
	 * 用户的id
	 */
	@ApiModelProperty(value = "员工的用户id，后端生成")
	private Long userId;

	//Username 属性
	@TableField(exist=false)
	@NotBlank(message="用户名不能为空+", groups = {AddGroup.class, UpdateGroup.class})
	private String username;
	//Username的password 属性
	@TableField(exist=false)
	@ApiModelProperty(value = "用户的密码")
	private String password;

	public EmployeeInfoEntity(Long userId) {
		this.userId = userId;
	}

	@TableField(exist=false)
	@ApiModelProperty(value = "用户的状态")
	private Integer status;
	@TableField(exist=false)
	private String roles;  //角色的名字
   // 校验密码是否被更改过
	private String initialPassword;
	@TableField(exist=false)
	private String departmentName;  //角色的名字
	@TableField(exist=false)
	private String groupsName;  //角色的名字

	@TableField(exist=false)
	private  Long issueTask;  //分配的

}
