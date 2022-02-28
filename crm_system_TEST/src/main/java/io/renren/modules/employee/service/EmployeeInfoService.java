package io.renren.modules.employee.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.entity.vo.TreeViewVo;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-01 10:28:14
 */
public interface EmployeeInfoService extends IService<EmployeeInfoEntity> {

    PageUtils queryPage(Map<String, Object> params, SysUserEntity user );
    IPage<EmployeeInfoEntity> selectPageAll(Page<EmployeeInfoEntity> page);
    IPage<EmployeeInfoEntity> selectPageAllQuery(Page<EmployeeInfoEntity> page, @Param(Constants.WRAPPER) QueryWrapper<EmployeeInfoEntity> ew, Map<String,Object> params, String codes, String currentUsername);

    @Deprecated
    IPage<EmployeeInfoEntity> getQueryPage(String currentRoleName, Map<String, Object> params, SysUserEntity user);



    @Deprecated
    EmployeeInfoEntity getInfo(String currentRoleName, SysUserEntity user, Long id);
    @Deprecated
    void saveEmployeeInfo(String currentRoleName, SysUserEntity currentUser, EmployeeInfoEntity employeeInfo);
    @Deprecated
    String reset(Long id, SysUserEntity currentUser);
    @Deprecated
    void updateEmployeeInfo(String currentRoleName, SysUserEntity currentUser, EmployeeInfoEntity employeeInfo);
    @Deprecated
    boolean isChangePassword(SysUserEntity currentUser);

    EmployeeInfoEntity queryUserId(Long userId);
    @Deprecated
    List<TreeViewVo> listByParentId(Long i);
    @Deprecated
    List<EmployeeInfoEntity> getNextLevelEmployeeList(SysUserEntity user);

    List<EmployeeInfoEntity> getNextLevelAllEmployeeList(SysUserEntity user);

    List<EmployeeInfoEntity> getAllEmployeeList();

    List<EmployeeInfoEntity> getNextLevelEmployeeListCustomer(SysUserEntity user);
}

