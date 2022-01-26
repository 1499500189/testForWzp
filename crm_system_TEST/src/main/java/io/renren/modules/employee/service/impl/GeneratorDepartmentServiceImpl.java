package io.renren.modules.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.employee.dao.GeneratorDepartmentDao;
import io.renren.modules.employee.entity.GeneratorDepartmentEntity;
import io.renren.modules.employee.service.GeneratorDepartmentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("generatorDepartmentService")
public class GeneratorDepartmentServiceImpl extends ServiceImpl<GeneratorDepartmentDao, GeneratorDepartmentEntity> implements GeneratorDepartmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GeneratorDepartmentEntity> page = this.page(
                new Query<GeneratorDepartmentEntity>().getPage(params),
                new QueryWrapper<GeneratorDepartmentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public GeneratorDepartmentEntity queryByName(String department) {

        return  baseMapper.queryByName(department);
    }

    @Override
    public GeneratorDepartmentEntity queryByNameTrue(String department) {
        return  baseMapper.queryByNameTrue(department);
    }


}
