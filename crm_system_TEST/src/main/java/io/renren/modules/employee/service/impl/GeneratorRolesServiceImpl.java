package io.renren.modules.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.employee.dao.GeneratorRolesDao;
import io.renren.modules.employee.entity.GeneratorRolesEntity;
import io.renren.modules.employee.service.GeneratorRolesService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("generatorRolesService")
public class GeneratorRolesServiceImpl extends ServiceImpl<GeneratorRolesDao, GeneratorRolesEntity> implements GeneratorRolesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GeneratorRolesEntity> page = this.page(
                new Query<GeneratorRolesEntity>().getPage(params),
                new QueryWrapper<GeneratorRolesEntity>()
        );

        return new PageUtils(page);
    }

}
