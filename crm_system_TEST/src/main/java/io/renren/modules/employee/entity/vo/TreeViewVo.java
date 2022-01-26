package io.renren.modules.employee.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2021 年 11 月 04 日
 */
@Data
public class TreeViewVo {
    @TableField(exist=false)
    private String ParentName;  //角色的父id的名字
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 公司
     */
    private String company;
    /**
     * 部门
     */
    private String department;
    /**
     * 小组
     */
    private String groups;
    /**
     * 用户的角色
     */

    private Long roleId;
    /**
     * 根据公司 部门小组和具体的角色加用户名一起去构成的
     */
    private String codes;
    /**
     * 0删除1，未删除
     */
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 用户的id
     */
    private Long userId;
    //Username 属性
    @TableField(exist=false)
    @NotBlank(message="用户名不能为空+", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    //Username的password 属性
    @TableField(exist=false)
    private Integer status;
    @TableField(exist=false)
    private String roles;  //角色的名字


    private List<TreeViewVo>  children =new ArrayList<>();
}
