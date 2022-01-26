package io.renren.modules.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.employee.entity.GeneratorGroupsEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-02 10:01:39
 */
public interface GeneratorGroupsService extends IService<GeneratorGroupsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    GeneratorGroupsEntity queryByName(String groups);

    GeneratorGroupsEntity queryByNameTrue(String groups);
}

