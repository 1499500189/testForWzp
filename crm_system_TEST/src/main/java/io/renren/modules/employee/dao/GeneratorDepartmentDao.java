package io.renren.modules.employee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.employee.entity.GeneratorDepartmentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-02 10:01:39
 */
@Mapper
public interface GeneratorDepartmentDao extends BaseMapper<GeneratorDepartmentEntity> {

    // 通过部门的int值获取到他的部门的对象
    GeneratorDepartmentEntity queryByName(String department);
//通过部门的真实的名字获取到对象
    GeneratorDepartmentEntity queryByNameTrue(String department);
}
