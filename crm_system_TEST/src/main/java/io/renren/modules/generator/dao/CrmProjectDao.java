package io.renren.modules.generator.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.generator.entity.CrmProjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 11:48:12
 */
@Mapper
public interface CrmProjectDao extends BaseMapper<CrmProjectEntity> {

    IPage<CrmProjectEntity> selectAllList(Page<?> page,@Param("params")Map<String, Object> params);
}
