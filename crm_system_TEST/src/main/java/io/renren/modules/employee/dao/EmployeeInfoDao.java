package io.renren.modules.employee.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-01 10:28:14
 */
@Mapper
public interface EmployeeInfoDao extends BaseMapper<EmployeeInfoEntity> {
    IPage<EmployeeInfoEntity> selectPageAll(IPage<EmployeeInfoEntity> page, @Param(Constants.WRAPPER)QueryWrapper<EmployeeInfoEntity> ew,
                                            @Param("department") String department, @Param("groups") String groups, @Param("role_name")String role
            , @Param("username") String username, @Param("codes") String codes, @Param("currentUsername") String currentUsername);

    EmployeeInfoEntity queryUserId(Long userId);
    EmployeeInfoEntity selectEmployeeInfo(Long Id);


    List<EmployeeInfoEntity> selectNextLevel(@Param(Constants.WRAPPER)QueryWrapper<EmployeeInfoEntity> ew);


    List<EmployeeInfoEntity>  getAllEmployeeList();
}
