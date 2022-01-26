package io.renren.modules.employee.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.entity.GeneratorDepartmentEntity;
import io.renren.modules.employee.entity.GeneratorGroupsEntity;
import io.renren.modules.employee.entity.vo.UserAllInfoVo;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.employee.service.GeneratorDepartmentService;
import io.renren.modules.employee.service.GeneratorGroupsService;
import io.renren.modules.employee.service.GeneratorRolesService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-01 10:28:14
 */
@Api(tags = "员工管理")
@RestController
@RequestMapping("/generator/employeeinfo")
public class EmployeeInfoController extends AbstractController {
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
     * 所有用户列表
     */
    @ApiOperation("展示员工列表")
    @GetMapping("/list")
    @RequiresPermissions("generator:employeeinfo:list")
    public R list(@ApiParam(value = "为条件查询的参数可以不进行传递参数，采用为params名称map集合进行接收" +
            "可以进行传递的参数为 page,limit,department,groups,roles,username") @RequestParam(required = false) Map<String, Object> params) {
        if (MapUtil.isEmpty(params)) {
            params = new HashMap<>();
        }
        SysUserEntity currentUser = getUser();
        //  employeeInfoService.selectEmployeeUser(user);
        SysRoleEntity roleEntity = checkUserIsLive(currentUser);
        String roleName = roleEntity.getRoleName();
        IPage<EmployeeInfoEntity> employeeInfoEntityIPage = employeeInfoService.getQueryPage(roleName, params, currentUser);
        return R.ok().put("page", new PageUtils(employeeInfoEntityIPage));
    }

    //验证用户是否具有一个角色， 不存在即报错  排除超级管理员
    private SysRoleEntity checkUserIsLive(SysUserEntity currentUser) {
        if (getUserId() != Constant.SUPER_ADMIN) {
            Long roleId = sysUserRoleService.queryRoleId(currentUser.getUserId());
            if (roleId == null) {
                throw new RRException("用户不存在角色", 400);
            }
            SysRoleEntity role = sysRoleService.getById(roleId);
            if (role == null) {
                throw new RRException("用户不存在角色", 400);
            }
            return role;
        }
        //为超级管理员的情况时
        SysRoleEntity role = new SysRoleEntity();
        role.setRoleId(0L);
        role.setRoleName("超级权限管理员");
        return role;
    }


    /**
     * 信息
     */
    @ApiOperation("展示员工的单个信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:employeeinfo:info")
    public R info(@ApiParam(value = "此方式必须传递要查询的员工的id", required = true) @PathVariable("id") Long id) {

        SysUserEntity currentUser = getUser();
        SysRoleEntity roleEntity = checkUserIsLive(currentUser);
        String roleName = roleEntity.getRoleName();
        EmployeeInfoEntity employeeInfo = employeeInfoService.getInfo(roleName, currentUser, id);
        return R.ok().put("employeeInfo", employeeInfo);
    }

    /**
     * 保存
     */
    @ApiOperation("保存一个员工的信息")
    @SysLog("生成员工，顺便添加用户")
    @PostMapping("/save")
    @RequiresPermissions("generator:employeeinfo:save")
    public R save(@ApiParam("传递一个为employeeInfo的对象，参数需要填写几个必要属性") @RequestBody EmployeeInfoEntity employeeInfo) {
        //先要判断他的权限;
        SysUserEntity currentUser = getUser();
        SysRoleEntity roleEntity = checkUserIsLive(currentUser);
        String roleName = roleEntity.getRoleName();
        employeeInfoService.saveEmployeeInfo(roleName, currentUser, employeeInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改员工的信息")
    @SysLog("修改员工信息，顺便修改了用户信息")
    @PostMapping("/update")
    @RequiresPermissions("generator:employeeinfo:update")
    public R update(@ApiParam("传递一个为employeeInfo的对象，参数需要填写几个必要属性") @RequestBody EmployeeInfoEntity employeeInfo) {
        //先要判断他的权限;
        SysUserEntity currentUser = getUser();
        SysRoleEntity roleEntity = checkUserIsLive(currentUser);
        String roleName = roleEntity.getRoleName();
        employeeInfoService.updateEmployeeInfo(roleName, currentUser, employeeInfo);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @ApiOperation("重置员工对应的角色的登录密码")
    @SysLog("重置密码")
    @GetMapping("/reset/{id}")
    @RequiresPermissions("generator:employeeinfo:reset")
    public R reset(@ApiParam(value = "传递需要修改员工的id值", required = true) @PathVariable Long id) {
        if (id == null) {
            throw new RRException("ID 不存在", 407);
        }
        //  employeeInfoService.updateById()
        String password = employeeInfoService.reset(id, getUser());

        return R.ok().put("password", password);
    }

    @ApiOperation("获取所有需要的数据字典")
    @GetMapping("/getAllDictionary")
    public R getAllDictionary() {
        List<GeneratorDepartmentEntity> department = generatorDepartmentService.getBaseMapper().selectList(null);
        List<GeneratorGroupsEntity> groups = generatorGroupsService.getBaseMapper().selectList(null);
        List<SysRoleEntity> roles = sysRoleService.getBaseMapper().selectList(null);
        //  List<Object>      company   =    new ArrayList<>();
        //  company.add("默认");
        HashMap<String, List> map = new HashMap<>();
        map.put("department", department);
        map.put("groups", groups);
        map.put("roles", roles);
        // map.put("company",company);
        return R.ok().put("page", map);
    }

    @ApiOperation("判断是否修改过密码")
    @GetMapping("/isChangePassword")
    public R isChangePassword() {
        //需要修改密码时true ，不需要修改密码是false
        SysUserEntity currentUser = getUser();
        /*    String password = getUser().getPassword();*/
        //获取到当前的用户id   ，  通过这个id去判断是否改过密码 ；
        boolean flag = employeeInfoService.isChangePassword(currentUser);

        return R.ok().put("flag", flag);
    }

    @ApiOperation("获取到当前的用户的信息")
    @GetMapping("/getUserInfo")
    //TODO 写的位置不太正确 ，之后可以修改位置
    public R getUserInfo() {
        SysUserEntity currentUser = getUser();
        UserAllInfoVo userAllInfoVo = new UserAllInfoVo();
        if (currentUser.getUserId() == Constant.SUPER_ADMIN) {//kankan
        }
        Long roleId = sysUserRoleService.queryRoleId(currentUser.getUserId());
        SysRoleEntity roleEntity = sysRoleService.getById(roleId);
        //返回用户信息和他的员工信息和他的权限 ，
        EmployeeInfoEntity employeeInfoEntity = employeeInfoService.queryUserId(currentUser.getUserId());

        if (employeeInfoEntity!=null){
            employeeInfoEntity.setRoles(roleEntity.getRoleName());
            employeeInfoEntity.setUsername(currentUser.getUsername());
            userAllInfoVo = new UserAllInfoVo();
            BeanUtils.copyProperties(employeeInfoEntity, userAllInfoVo);
            String department = userAllInfoVo.getDepartment();
            if (StringUtils.isBlank(department)) {
                userAllInfoVo.setDepartmentName(null);
            } else {
                QueryWrapper<GeneratorDepartmentEntity> gd = new QueryWrapper<>();
                gd.eq("department_int", department);
                userAllInfoVo.setDepartmentName(generatorDepartmentService.getOne(gd).getDepartmentName());
            }
            String groups = userAllInfoVo.getGroups();
            if (StringUtils.isBlank(groups)) {
                userAllInfoVo.setGroups(null);
            } else {
                QueryWrapper<GeneratorGroupsEntity> gd = new QueryWrapper<>();
                gd.eq("groups_int", groups);
                userAllInfoVo.setGroupsName(generatorGroupsService.getOne(gd).getGroupsName());
            }

        }
        else {

            userAllInfoVo.setUsername(currentUser.getUsername());
            userAllInfoVo.setRoles(roleEntity.getRoleName());
        }

        return R.ok().put("user", userAllInfoVo);
    }


    /**
     * 修改员工的状态，可以禁用用户登录
     */
    @ApiOperation("修改员工是否可以登录的状态")
    @SysLog("修改员工的状态，用户的状态")
    @GetMapping("/updateStatus/{id}")
    @RequiresPermissions("generator:employeeinfo:save")
    public R updateStatus(@ApiParam("传递一个员工的id") @PathVariable Long id) {

        EmployeeInfoEntity employeeInfoEntity = employeeInfoService.getById(id);
        Long userId = employeeInfoEntity.getUserId();
        SysUserEntity sysUserEntity = sysUserService.getById(userId);
        if (sysUserEntity.getStatus() == 1) {
            sysUserEntity.setStatus(0);
            sysUserService.updateById(sysUserEntity);
            return R.ok().put("status", 0);
        } else {
            sysUserEntity.setStatus(1);
            sysUserService.updateById(sysUserEntity);
            return R.ok().put("status", 1);
        }

    }


}
