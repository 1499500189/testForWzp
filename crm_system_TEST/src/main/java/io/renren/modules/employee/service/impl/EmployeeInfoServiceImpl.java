package io.renren.modules.employee.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.MapUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.modules.employee.constant.ConstantRoles;
import io.renren.modules.employee.dao.EmployeeInfoDao;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.entity.GeneratorDepartmentEntity;
import io.renren.modules.employee.entity.GeneratorGroupsEntity;
import io.renren.modules.employee.entity.vo.TreeViewVo;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.employee.service.GeneratorDepartmentService;
import io.renren.modules.employee.service.GeneratorGroupsService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("employeeInfoService")
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoDao, EmployeeInfoEntity> implements EmployeeInfoService {


    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
@Autowired
private GeneratorDepartmentService generatorDepartmentService;
@Autowired
private GeneratorGroupsService generatorGroupsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, SysUserEntity user ) {
        String key = (String) params.get("key");
        IPage<EmployeeInfoEntity> page = this.page(
                new Query<EmployeeInfoEntity>().getPage(params),
                new QueryWrapper<EmployeeInfoEntity>().like(!key.isEmpty(), "company",key)
        );

        return new PageUtils(page);
    }

    @Override
    public IPage<EmployeeInfoEntity> selectPageAll(Page<EmployeeInfoEntity> page) {


        return baseMapper.selectPageAll(page,null,null,null,null,null,null,null);
    }

    @Override
    public IPage<EmployeeInfoEntity> selectPageAllQuery(Page<EmployeeInfoEntity> page, QueryWrapper<EmployeeInfoEntity> ew, Map<String,Object> params, String codes, String currentUsername) {
       /* if (StringUtils.isNotBlank((String)params.get("roles"))){
            Long roleId = sysRoleService.queryRoleName((String) params.get("roles"));

        }
*/
        return baseMapper.selectPageAll(page,ew,(String) params.get("department"),(String) params.get("groups"),
                (String) params.get("roles"),(String) params.get("username"),codes,currentUsername);
    }

    @Transactional
    public void updateEmployeeInfoTempBusiness(String currentRoleName, SysUserEntity currentUser, EmployeeInfoEntity employeeInfo) {
        //只能选择更新用户表的状态和名字
        //能更新员工表的所有的字段
        String username = employeeInfo.getUsername();
        if (StringUtils.isBlank(username)){
            throw  new RRException("姓名字段不能为空",407);
        }
        Integer status = employeeInfo.getStatus();
        if (status==null){
            throw  new RRException("状态字段不能为空",407);
        }
        if (StringUtils.isBlank(employeeInfo.getRoles())){
            throw  new RRException("角色不能为空",407);
        }
        //密码采取默认的
        if (currentRoleName.equals("超级权限管理员")){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)){
                throw  new RRException("请添加部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (!StringUtils.isBlank(groups)){
                throw  new RRException("总经理不能添加组别信息",407);
            }
            if (!employeeInfo.getRoles().equals(ConstantRoles.OW)){
                throw  new RRException("总经理只能添加总监的信息",407);
            }
        }
        if (currentRoleName.equals(ConstantRoles.OW)){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)||!department.equals(baseMapper.queryUserId(currentUser.getUserId()).getDepartment())){
                throw  new RRException("部门信息不正确，只能添加自己部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (StringUtils.isBlank(groups)){
                throw  new RRException("组别信息不能为空,总监创建小组",407);
            }
            String roles = employeeInfo.getRoles();
            if (roles.equals(ConstantRoles.staff)){
                throw  new RRException("总监不能添加普通员工",407);
            }
            // Long roleId = employeeInfo.getRoleId();
            // SysRoleEntity roleEntity = sysRoleService.getById(roleId);
            if (roles.equals(ConstantRoles.OW)||roles.equals("超级权限管理员")){
                throw  new RRException("创建的角色权限大于您的权限",407);
            }
        }
        if (currentRoleName.equals(ConstantRoles.executiveDirector)){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)||!department.equals(baseMapper.queryUserId(currentUser.getUserId()).getDepartment())){
                throw  new RRException("部门信息不正确，只能添加自己部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (StringUtils.isBlank(groups)||!groups.equals(baseMapper.queryUserId(currentUser.getUserId()).getGroups())){
                throw  new RRException("组别信息不正确，只能添加自己组别",407);
            }
            //  Long roleId = employeeInfo.getRoleId();
            // SysRoleEntity roleEntity = sysRoleService.getById(roleId);
            String roles = employeeInfo.getRoles();

            if (!roles.equals(ConstantRoles.staff)){
                throw  new RRException("只能添加自己小组的员工信息,角色选择错误",407);
            }
        }
        if ((!currentRoleName.equals(ConstantRoles.OW))&&(!currentRoleName.equals(ConstantRoles.executiveDirector))&&(!currentRoleName.equals("超级权限管理员")))
        {
            throw  new RRException("权限不足，不能添加角色",407);
        }




        // Long userId = employeeInfo.getUserId();
        employeeInfo.setRoleId(sysRoleService.queryRoleName(employeeInfo.getRoles()));
        employeeInfo.setUpdateTime(new Date());
        // employeeInfo.setIsDeleted(status);

        //数据的userid需要从数据库获取 ，否则空指针
        EmployeeInfoEntity databaseEmployee = baseMapper.selectById(employeeInfo.getId());
        SysUserEntity userEntity = sysUserService.getById(databaseEmployee.getUserId());
        userEntity.setUsername(username);
        userEntity.setStatus(status);
        sysUserService.updateById(userEntity);
        //TODO,更新code字段
        String  code = "";
        if (employeeInfo.getRoles().equals(ConstantRoles.staff)){
            //普通员工的codes值才具有id的字段
            code =  employeeInfo.getId()+code;
        }
        if (StringUtils.isNotBlank(employeeInfo.getGroups())){
            code =  employeeInfo.getGroups()+code;
        }
        if (StringUtils.isNotBlank(employeeInfo.getDepartment())){
            code =  employeeInfo.getDepartment()+code;
        }
        //设置初始密码和和code
        employeeInfo.setCodes("00"+code);
        baseMapper.updateById(employeeInfo);



        //先删除用户与角色关系
        sysUserRoleService.removeByMap(new MapUtils().put("user_id", databaseEmployee.getUserId()));
        //设置角色和权限的关系
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(databaseEmployee.getUserId());
        sysUserRoleEntity.setRoleId(sysRoleService.queryRoleName(employeeInfo.getRoles()));
        sysUserRoleService.save(sysUserRoleEntity);

    }


    @Override //todo 超级权限管理员 //roleName 为当前登录的用户的角色名字
    public IPage<EmployeeInfoEntity> getQueryPage(String roleName, Map<String, Object> params, SysUserEntity user) {
        //分页参数
        long curPage = 1;
        long limit = 10;
        if (params==null){
            params =new HashMap<String, Object>();
            params.put("1",1);
        }
            if(params.get(Constant.PAGE)!=null){
                curPage = Long.parseLong((String)params.get(Constant.PAGE));
            }
            if(params.get(Constant.LIMIT) != null){
                limit = Long.parseLong((String)params.get(Constant.LIMIT));
            }

        IPage<EmployeeInfoEntity> employeeInfoEntityIPage = null;
        Page<EmployeeInfoEntity> page  = new Page<>(curPage,limit);
        QueryWrapper<EmployeeInfoEntity> employeeInfoEntityQueryWrapper = new QueryWrapper<>();
        //设置请求中携带的参数
        if (roleName.equals("超级权限管理员")){
            //查所有的,不需要设置权限
            EmployeeInfoEntity employeeInfoEntity        = baseMapper.queryUserId(user.getUserId());
            String codes = employeeInfoEntity.getCodes();
          //  employeeInfoEntityQueryWrapper.likeRight("codes",codes);
            employeeInfoEntityIPage = this.selectPageAllQuery(page,employeeInfoEntityQueryWrapper,params,codes,user.getUsername());
            return employeeInfoEntityIPage;
        }
        if (roleName.equals(ConstantRoles.OW)){
            //只能查询自己部门的员工   ,先知道他的所属部门
           EmployeeInfoEntity employeeInfoEntity        = baseMapper.queryUserId(user.getUserId());
           //  employeeInfoEntityQueryWrapper.eq("username",user.getUsername());
            String codes = employeeInfoEntity.getCodes();
            employeeInfoEntityIPage = this.selectPageAllQuery(page,employeeInfoEntityQueryWrapper,params,codes,user.getUsername());
            return employeeInfoEntityIPage;
        }
        if (roleName.equals(ConstantRoles.executiveDirector)){
            //只能查询自己小组的员工
            EmployeeInfoEntity employeeInfoEntity        = baseMapper.queryUserId(user.getUserId());
            String codes = employeeInfoEntity.getCodes();
          //  employeeInfoEntityQueryWrapper.likeRight("codes",codes);

            employeeInfoEntityIPage = this.selectPageAllQuery(page,employeeInfoEntityQueryWrapper,params,codes,user.getUsername());
            return employeeInfoEntityIPage;
        }
        throw  new RRException("chuxinaquanxianwenti",407);
      /*  if (roleName.equals("普通员工")){
            String key = (String) params.get("key");
            //System.out.println("key的 值为"+key);
            //自己的
            page = new Page<>(curPage,limit);
            employeeInfoEntityQueryWrapper.eq("username",user.getUsername());

            employeeInfoEntityIPage = this.selectPageAllQuery(page, employeeInfoEntityQueryWrapper);
            return employeeInfoEntityIPage;
        }*/
    }
    @Override //TODO 超级权限管理员
    public EmployeeInfoEntity getInfo(String currentRoleName, SysUserEntity currentUser, Long id) {
        EmployeeInfoEntity employeeInfoEntity =null;
        if (currentRoleName.equals(ConstantRoles.OW)||currentRoleName.equals("超级权限管理员")) {
            employeeInfoEntity = baseMapper.selectEmployeeInfo(id);
        }
        if (currentRoleName.equals(ConstantRoles.OW)){
/*
            EmployeeInfoEntity employeeInfoEntitySelect = baseMapper.queryUserId(currentUser.getUserId());
            if (!employeeInfoEntity.getDepartment().equals(employeeInfoEntitySelect.getDepartment())){
                throw  new RRException("用户查找的员工信息权限不足",410);
            }*/
            //employeeInfoEntity =baseMapper.selectById(id);
            employeeInfoEntity = baseMapper.selectEmployeeInfo(id);
        }
        if (currentRoleName.equals(ConstantRoles.executiveDirector)){
          /*  EmployeeInfoEntity employeeInfoEntitySelect = baseMapper.queryUserId(currentUser.getUserId());
            if (!employeeInfoEntity.getDepartment().equals(employeeInfoEntitySelect.getDepartment())){
                throw  new RRException("用户查找的员工信息权限不足",410);
            }
            if (!employeeInfoEntity.getGroups().equals(employeeInfoEntitySelect.getGroups())){
                throw  new RRException("用户查找的员工信息权限不足",410);
            }*/
            employeeInfoEntity = baseMapper.selectEmployeeInfo(id);
        }
        /*if (currentRoleName.equals("普通员工")){  普通用户不具备这个模块
            Long userId = currentUser.getUserId();
            employeeInfoEntity = baseMapper.selectEmployeeInfo(userId);
        //    employeeInfoEntity =baseMapper.selectById(id);
         //   EmployeeInfoEntity employeeInfoEntitySelect = baseMapper.queryUserId(user.getUserId());
         //   if (user.getUsername()!=employeeInfoEntity.get)
        }*/
        return  employeeInfoEntity;
    }

    @Override
    @Transactional
    public void saveEmployeeInfo(String currentRoleName, SysUserEntity currentUser, EmployeeInfoEntity employeeInfo) {

        if (StringUtils.isBlank(employeeInfo.getUsername())){
            throw  new RRException("姓名字段不能为空",407);
        }
        if (StringUtils.isBlank(employeeInfo.getRoles())){
            throw  new RRException("职位字段不能为空",407);
        }
        //密码采取默认的
        if (currentRoleName.equals("超级权限管理员")){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)){
                throw  new RRException("请添加部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (!StringUtils.isBlank(groups)){
                throw  new RRException("总经理不能添加组别信息",407);
            }
            if (!employeeInfo.getRoles().equals(ConstantRoles.OW)){
                throw  new RRException("总经理只能添加总监的信息",407);
            }
            createSysUserAndEmployee(currentRoleName,currentUser,employeeInfo);

        }
        if (currentRoleName.equals(ConstantRoles.OW)){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)||!department.equals(baseMapper.queryUserId(currentUser.getUserId()).getDepartment())){
                throw  new RRException("部门信息不正确，只能添加自己部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (StringUtils.isBlank(groups)){
                throw  new RRException("组别信息不能为空,总监创建小组",407);
            }
            String roles = employeeInfo.getRoles();
            if (roles.equals(ConstantRoles.staff)){
                throw  new RRException("总监不能添加普通员工",407);
            }
            // Long roleId = employeeInfo.getRoleId();
           // SysRoleEntity roleEntity = sysRoleService.getById(roleId);
            if (roles.equals(ConstantRoles.OW)||roles.equals("超级权限管理员")){
                throw  new RRException("创建的角色权限大于您的权限",407);
            }
            createSysUserAndEmployee(currentRoleName,currentUser,employeeInfo);
        }


        if (currentRoleName.equals(ConstantRoles.executiveDirector)){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)||!department.equals(baseMapper.queryUserId(currentUser.getUserId()).getDepartment())){
                throw  new RRException("部门信息不正确，只能添加自己部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (StringUtils.isBlank(groups)||!groups.equals(baseMapper.queryUserId(currentUser.getUserId()).getGroups())){
                throw  new RRException("组别信息不正确，只能添加自己组别",407);
            }
          //  Long roleId = employeeInfo.getRoleId();
           // SysRoleEntity roleEntity = sysRoleService.getById(roleId);
            String roles = employeeInfo.getRoles();

            if (!roles.equals(ConstantRoles.staff)){
                throw  new RRException("只能添加自己小组的员工信息,角色选择错误",407);
            }
            createSysUserAndEmployee(currentRoleName,currentUser,employeeInfo);
        }
    if ((!currentRoleName.equals(ConstantRoles.OW))&&(!currentRoleName.equals(ConstantRoles.executiveDirector))&&(!currentRoleName.equals("超级权限管理员")))
        {
            throw  new RRException("权限不足，不能添加角色",407);
        }
    }

    @Override
    public String reset(Long id, SysUserEntity currentUser) {
        EmployeeInfoEntity employeeInfoEntity = baseMapper.selectById(id);

        //  查看权限
        QueryWrapper<EmployeeInfoEntity> employeeInfoEntityQueryWrapper = new QueryWrapper<>();
        employeeInfoEntityQueryWrapper.likeRight("codes",baseMapper.queryUserId(currentUser.getUserId()).getCodes());
        employeeInfoEntityQueryWrapper.eq("codes",employeeInfoEntity.getCodes());
        List<EmployeeInfoEntity> infoEntityList = baseMapper.selectList(employeeInfoEntityQueryWrapper);
         if (infoEntityList!=null){
             if (infoEntityList.size()<=0){
               throw  new RRException("没有权限",407);
             }
         }


        employeeInfoEntity.setUpdateTime(new Date());
        baseMapper.updateById(employeeInfoEntity);
        Long userId = employeeInfoEntity.getUserId();
        SysUserEntity sysUserEntity = sysUserService.getById(userId);
         int   pws =      (int)((Math.random()*9+1)*100000);
        String   password  = pws+"";
        sysUserEntity.setPassword(password);
        //根据   进行添加的校验    用户的添加属性，将密码和用户的salt进                             行加密处理
        ValidatorUtils.validateEntity(sysUserEntity, AddGroup.class);
        String salt = sysUserEntity.getSalt();
        // String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUserEntity.setPassword(new Sha256Hash(password, salt).toHex());
      //  sysUserEntity.setInitialPassword(new Sha256Hash(password, salt).toHex());
        sysUserEntity.setSalt(salt);
        sysUserService.updateById(sysUserEntity);
       // sysUserService.update();
        return  password;
    }
    @Transactional
    @Override
    public void updateEmployeeInfo(String currentRoleName, SysUserEntity currentUser, EmployeeInfoEntity employeeInfo) {
          //只能选择更新用户表的状态和名字
           //能更新员工表的所有的字段
        String username = employeeInfo.getUsername();
        if (StringUtils.isBlank(username)){
            throw  new RRException("姓名字段不能为空",407);
        }
        Integer status = employeeInfo.getStatus();
        if (status==null){
            throw  new RRException("状态字段不能为空",407);
        }
        if (StringUtils.isBlank(employeeInfo.getRoles())){
            throw  new RRException("角色不能为空",407);
        }
        //密码采取默认的
        if (currentRoleName.equals("超级权限管理员")){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)){
                throw  new RRException("请添加部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (!StringUtils.isBlank(groups)){
                throw  new RRException("总经理不能添加组别信息",407);
            }
            if (!employeeInfo.getRoles().equals(ConstantRoles.OW)){
                throw  new RRException("总经理只能添加总监的信息",407);
            }
        }
        if (currentRoleName.equals(ConstantRoles.OW)){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)||!department.equals(baseMapper.queryUserId(currentUser.getUserId()).getDepartment())){
                throw  new RRException("部门信息不正确，只能添加自己部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (StringUtils.isBlank(groups)){
                throw  new RRException("组别信息不能为空,总监创建小组",407);
            }
            String roles = employeeInfo.getRoles();
            if (roles.equals(ConstantRoles.staff)){
                throw  new RRException("总监不能添加普通员工",407);
            }
            // Long roleId = employeeInfo.getRoleId();
            // SysRoleEntity roleEntity = sysRoleService.getById(roleId);
            if (roles.equals(ConstantRoles.OW)||roles.equals("超级权限管理员")){
                throw  new RRException("创建的角色权限大于您的权限",407);
            }
        }
        if (currentRoleName.equals(ConstantRoles.executiveDirector)){
            String department = employeeInfo.getDepartment();
            if (StringUtils.isBlank(department)||!department.equals(baseMapper.queryUserId(currentUser.getUserId()).getDepartment())){
                throw  new RRException("部门信息不正确，只能添加自己部门",407);
            }
            String groups = employeeInfo.getGroups();
            if (StringUtils.isBlank(groups)||!groups.equals(baseMapper.queryUserId(currentUser.getUserId()).getGroups())){
                throw  new RRException("组别信息不正确，只能添加自己组别",407);
            }
            //  Long roleId = employeeInfo.getRoleId();
            // SysRoleEntity roleEntity = sysRoleService.getById(roleId);
            String roles = employeeInfo.getRoles();

            if (!roles.equals(ConstantRoles.staff)){
                throw  new RRException("只能添加自己小组的员工信息,角色选择错误",407);
            }
        }
        if ((!currentRoleName.equals(ConstantRoles.OW))&&(!currentRoleName.equals(ConstantRoles.executiveDirector))&&(!currentRoleName.equals("超级权限管理员")))
        {
            throw  new RRException("权限不足，不能添加角色",407);
        }




       // Long userId = employeeInfo.getUserId();
        employeeInfo.setRoleId(sysRoleService.queryRoleName(employeeInfo.getRoles()));
        employeeInfo.setUpdateTime(new Date());
       // employeeInfo.setIsDeleted(status);

        //数据的userid需要从数据库获取 ，否则空指针
        EmployeeInfoEntity databaseEmployee = baseMapper.selectById(employeeInfo.getId());
        SysUserEntity userEntity = sysUserService.getById(databaseEmployee.getUserId());
        userEntity.setUsername(username);
        userEntity.setStatus(status);
        sysUserService.updateById(userEntity);
        //TODO,更新code字段
        String  code = "";
        if (employeeInfo.getRoles().equals(ConstantRoles.staff)){
            //普通员工的codes值才具有id的字段
            code =  employeeInfo.getId()+code;
        }
        if (StringUtils.isNotBlank(employeeInfo.getGroups())){
            code =  employeeInfo.getGroups()+code;
        }
        if (StringUtils.isNotBlank(employeeInfo.getDepartment())){
            code =  employeeInfo.getDepartment()+code;
        }
        //设置初始密码和和code
        employeeInfo.setCodes("00"+code);
        baseMapper.updateById(employeeInfo);



        //先删除用户与角色关系
        sysUserRoleService.removeByMap(new MapUtils().put("user_id", databaseEmployee.getUserId()));
        //设置角色和权限的关系
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(databaseEmployee.getUserId());
        sysUserRoleEntity.setRoleId(sysRoleService.queryRoleName(employeeInfo.getRoles()));
        sysUserRoleService.save(sysUserRoleEntity);

    }

    @Override
    public boolean isChangePassword(SysUserEntity currentUser) {
        //需要修改密码时true ，不需要修改密码是false
        Long userId = currentUser.getUserId();
        String password = currentUser.getPassword();

        EmployeeInfoEntity employeeInfoEntity = baseMapper.queryUserId(userId);
        if (employeeInfoEntity!=null){
            if (employeeInfoEntity.getInitialPassword().equals(password)){
                return  true;
            }
            else {
                return  false;
            }
        }else {
            return  false;
        }


    }

    @Override
    public EmployeeInfoEntity queryUserId(Long userId) {
    return  baseMapper.queryUserId(userId);
    }



    @Transactional
     void createSysUserAndEmployee(String currentRoleName, SysUserEntity currentUser, EmployeeInfoEntity employeeInfo) {
        //TODO 创建一个角色 ，目前还需要看看   进行验证权限 ，只能添加自己职能之内的权限
        if (StringUtils.isBlank(employeeInfo.getRoles())){
            throw  new RRException("用户的角色不能为空",407);
        }
        //完善员工的一些信息 ，之后创建一个用于登录的用户
        SysUserEntity sysUserEntity = new SysUserEntity();
        employeeInfo.setCreateTime(new Date());
        employeeInfo.setUpdateTime(new Date());
        employeeInfo.setCompany("00");

        //employeeInfo.setRoleId(sysRoleService.queryRoleName(currentRoleName));
        employeeInfo.setRoleId(sysRoleService.queryRoleName(employeeInfo.getRoles()));
        if (employeeInfo.getStatus()==null){
            throw  new RRException("状态字段不能为空",407);
        }
       // employeeInfo.setIsDeleted(employeeInfo.getStatus());
        if (currentRoleName=="超级权限管理员") {
            employeeInfo.setParentId(0l);
            sysUserEntity.setCreateUserId(1l);
            sysUserEntity.setMobile("12345678909");
        }else {
            employeeInfo.setParentId(baseMapper.queryUserId(currentUser.getUserId()).getId());
            sysUserEntity.setCreateUserId(currentUser.getUserId());
            sysUserEntity.setMobile("12345678909");
        }

        sysUserEntity.setCreateTime(new Date());
       // sysUserEntity.setStatus(employeeInfo.getIsDeleted());
        sysUserEntity.setUsername(employeeInfo.getUsername());
        sysUserEntity.setPassword("000000");
        sysUserEntity.setEmail("149950018@qq.com");

        //根据   进行添加的校验    用户的添加属性，将密码和用户的salt进                                  行加密处理
        ValidatorUtils.validateEntity(sysUserEntity, AddGroup.class);
        ValidatorUtils.validateEntity(employeeInfo, AddGroup.class);
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = new Sha256Hash(sysUserEntity.getPassword(), salt).toHex();
        sysUserEntity.setPassword(password);
        sysUserEntity.setSalt(salt);
        sysUserEntity.setStatus(employeeInfo.getStatus());
        sysUserService.save(sysUserEntity);


        Long userId = sysUserService.queryByUserName(employeeInfo.getUsername()).getUserId();
        employeeInfo.setUserId(userId);
        baseMapper.insert(employeeInfo);
        //用userId去取对象，利用company ，department ，groups   ， id 这些字段构成codes 的值 ，继续保存
        EmployeeInfoEntity employeeInfoEntity = baseMapper.queryUserId(userId);
        String  code = "";
        if (employeeInfo.getRoles().equals(ConstantRoles.staff)){
            //普通员工的codes值才具有id的字段
            code =  employeeInfoEntity.getId()+code;
        }
        if (StringUtils.isNotBlank(employeeInfoEntity.getGroups())){
            code =  employeeInfoEntity.getGroups()+code;
        }

        if (StringUtils.isNotBlank(employeeInfoEntity.getDepartment())){
            code =  employeeInfoEntity.getDepartment()+code;
        }
        //设置初始密码和和code
        employeeInfoEntity.setInitialPassword(password);
        employeeInfoEntity.setCodes(employeeInfoEntity.getCompany()+code);
        baseMapper.updateById(employeeInfoEntity);


        //先删除用户与角色（权限）关系
        sysUserRoleService.removeByMap(new MapUtils().put("user_id", userId));
        //设置用户和角色（权限）的关系
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(userId);
        sysUserRoleEntity.setRoleId(sysRoleService.queryRoleName(employeeInfo.getRoles()));
        sysUserRoleService.save(sysUserRoleEntity);
        // this.save(user);
    }

  //通过父节点去获取到他的子节点
    @Override
    public List<TreeViewVo> listByParentId(Long parentId) {

        List<TreeViewVo> treeViewVoList = new ArrayList<>();

        List<EmployeeInfoEntity> eList = baseMapper.selectList(new QueryWrapper<EmployeeInfoEntity>().eq("parent_id", parentId).orderByAsc("department").orderByAsc("groups"));
        eList.forEach(e -> {
            TreeViewVo treeViewVo = new TreeViewVo();
            Long userId = e.getUserId();
            SysUserEntity byId = sysUserService.getById(userId);
            String username = byId.getUsername();
            e.setUsername(username);
            BeanUtils.copyProperties(e, treeViewVo);

            treeViewVoList.add(treeViewVo);
       if  (hasChildren(e.getId())){
           List<TreeViewVo> children = listByParentId(e.getId());
           treeViewVo.setChildren(children);
       }
        });

        return treeViewVoList;
    }

    @Override//获取到当前的下一级的 ，不包含下下级
    public List<EmployeeInfoEntity> getNextLevelEmployeeList(SysUserEntity user) {
        QueryWrapper<EmployeeInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gd.department_id");
        Long roleId = sysUserRoleService.queryRoleId(user.getUserId());

        if (ConstantRoles.OW.equals(sysRoleService.getById(roleId).getRoleName())){
            wrapper.ne("groups","");
            wrapper.ne("ei.user_id",user.getUserId());
            wrapper.likeRight("codes",baseMapper.queryUserId(user.getUserId()).getCodes());
            List<EmployeeInfoEntity> employeeInfoEntities = baseMapper.selectNextLevel(wrapper);

            Iterator it = employeeInfoEntities.iterator();
            while (it.hasNext()){
                EmployeeInfoEntity employee= (EmployeeInfoEntity)it.next();
                if (StringUtils.isNotBlank(employee.getCodes())){
                    if (employee.getCodes().length()>6){
                        it.remove();
                    }
                }
            }

            return   employeeInfoEntities;
        }
        if (ConstantRoles.executiveDirector.equals(sysRoleService.getById(roleId).getRoleName())){
            wrapper.ne("ei.user_id",user.getUserId());
            wrapper.likeRight("codes",baseMapper.queryUserId(user.getUserId()).getCodes());
            return  baseMapper.selectNextLevel(wrapper);
        }



        return null;
    }

    @Override  //获取到当前的所有下级，包含下下级
    public List<EmployeeInfoEntity> getNextLevelAllEmployeeList(SysUserEntity user) {
        QueryWrapper<EmployeeInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gd.department_id");
            wrapper.likeRight("codes",baseMapper.queryUserId(user.getUserId()).getCodes());
        List<EmployeeInfoEntity> infoEntityList = baseMapper.selectNextLevel(wrapper);
        for (EmployeeInfoEntity e: infoEntityList) {
            if (e.getGroups()!=null&&!e.getGroups().equals(""))
            {
                String groups_id = e.getGroups();
                GeneratorGroupsEntity generatorGroupsEntity = generatorGroupsService.queryByName(groups_id);
                e.setUsername( generatorGroupsEntity.getGroupsName()+e.getUsername());
            }
            if (e.getDepartment()!=null&&!e.getDepartment().equals(""))
            {
                String department_int = e.getDepartment();
                GeneratorDepartmentEntity generatorDepartmentEntity = generatorDepartmentService.queryByName(department_int);
                e.setUsername( generatorDepartmentEntity.getDepartmentName()+e.getUsername());
            }
        }
        return    infoEntityList;
    }

    @Override
    public List<EmployeeInfoEntity> getAllEmployeeList() {
        List<EmployeeInfoEntity> allEmployeeList = baseMapper.getAllEmployeeList();
        return allEmployeeList;
    }

    @Override//客户模块的下拉框 ， 查询组别使用
    public List<EmployeeInfoEntity> getNextLevelEmployeeListCustomer(SysUserEntity user) {
        QueryWrapper<EmployeeInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gd.department_id");
        wrapper.likeRight("codes",baseMapper.queryUserId(user.getUserId()).getCodes());
        List<EmployeeInfoEntity> infoEntityList = baseMapper.selectNextLevel(wrapper);
        return    infoEntityList;
    }


    /**
     * 判断该节点是否有子节点
     */
    private boolean hasChildren(Long id) {
        QueryWrapper<EmployeeInfoEntity> queryWrapper = new QueryWrapper<EmployeeInfoEntity>().eq("parent_id", id);
       // List<EmployeeInfoEntity> employeeInfoEntities = baseMapper.selectList(queryWrapper);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count.intValue() > 0) {
            return true;
        }
        return false;
    }

}
