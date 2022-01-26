package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CrmCategoryDao;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.service.CrmCategoryService;


@Service("crmCategoryService")
public class CrmCategoryServiceImpl extends ServiceImpl<CrmCategoryDao, CrmCategoryEntity> implements CrmCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmCategoryEntity> page = this.page(
                new Query<CrmCategoryEntity>().getPage(params),
                new QueryWrapper<CrmCategoryEntity>()
        );

        return new PageUtils(page);
    }

}