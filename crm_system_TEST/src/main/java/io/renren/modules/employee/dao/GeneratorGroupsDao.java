package io.renren.modules.employee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.employee.entity.GeneratorGroupsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-02 10:01:39
 */
@Mapper
public interface GeneratorGroupsDao extends BaseMapper<GeneratorGroupsEntity> {
         //通过小组的int值取匹配
    GeneratorGroupsEntity queryByName(String groups);
    //通过小组的name值取匹配到对象
    GeneratorGroupsEntity queryByNameTrue(String groups);
}
