package io.renren.modules.employee.controller;

import io.renren.common.utils.R;

import io.renren.modules.employee.entity.vo.TreeViewVo;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.employee.service.GeneratorDepartmentService;
import io.renren.modules.employee.service.GeneratorGroupsService;
import io.renren.modules.employee.service.GeneratorRolesService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-01 10:28:14
 */
@Api(tags = "获取到树状图")
@RestController
@RequestMapping("/generator/treeView")
public class TreeViewController extends AbstractController {
    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private GeneratorRolesService generatorRolesService;
    @Autowired
    private GeneratorDepartmentService generatorDepartmentService;
    @Autowired
    private GeneratorGroupsService generatorGroupsService;

    /**
     * 展示组织架构  权限存在问题 ，requiresPermissions需要添加
     */
    @ApiOperation("获取到树状图列表")
    @GetMapping("/list")
    //@RequiresPermissions("generator:employeeinfo:list")
    public R list(){
        List<TreeViewVo> childNode = employeeInfoService.listByParentId(0l);
     return R.ok().put("childNode", childNode);
    }
}
