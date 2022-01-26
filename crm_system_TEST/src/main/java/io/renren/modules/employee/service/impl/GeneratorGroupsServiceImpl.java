package io.renren.modules.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.employee.dao.GeneratorGroupsDao;
import io.renren.modules.employee.entity.GeneratorGroupsEntity;
import io.renren.modules.employee.service.GeneratorGroupsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("generatorGroupsService")
public class GeneratorGroupsServiceImpl extends ServiceImpl<GeneratorGroupsDao, GeneratorGroupsEntity> implements GeneratorGroupsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GeneratorGroupsEntity> page = this.page(
                new Query<GeneratorGroupsEntity>().getPage(params),
                new QueryWrapper<GeneratorGroupsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public GeneratorGroupsEntity queryByName(String groups) {
        return baseMapper.queryByName(groups);
    }

    @Override
    public GeneratorGroupsEntity queryByNameTrue(String groups) {
        return baseMapper.queryByNameTrue(groups);
    }

}
