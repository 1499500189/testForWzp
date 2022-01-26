package io.renren.modules.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.employee.entity.GeneratorDepartmentEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-02 10:01:39
 */
public interface GeneratorDepartmentService extends IService<GeneratorDepartmentEntity> {

    PageUtils queryPage(Map<String, Object> params);

 // 通过部门的int值获取到他的部门的对象
    GeneratorDepartmentEntity queryByName(String department);

    //通过部门的name值 获取到他的部门的对象
    GeneratorDepartmentEntity queryByNameTrue(String department);
}

