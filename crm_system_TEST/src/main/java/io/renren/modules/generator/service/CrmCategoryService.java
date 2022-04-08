package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CrmCategoryEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 13:42:08
 */
public interface CrmCategoryService extends IService<CrmCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveWork();

}

