package io.renren.modules.generator.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.generator.entity.CrmWorkloadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-24 14:20:25
 */
@Mapper
public interface CrmWorkloadDao extends BaseMapper<CrmWorkloadEntity> {

    IPage<CrmWorkloadEntity> selectWorkloadList(Page<CrmWorkloadEntity> iPage, Map<String, Object> params);
}
