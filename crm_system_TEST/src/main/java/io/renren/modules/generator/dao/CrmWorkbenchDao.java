package io.renren.modules.generator.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.generator.entity.vo.ChartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 15:00:52
 */
@Mapper
public interface CrmWorkbenchDao extends BaseMapper<CrmWorkbenchEntity> {

    IPage<CrmWorkbenchEntity> selectWorkbenchList(Page<?> page,@Param("params") Map<String, Object> params);

    List<CrmWorkbenchEntity> selectColumn(@Param("params")Map<String, Object> params);

    IPage<ChartVo> getSummaryList(Page<?> page,@Param("params")Map<String, Object> params);

    List<ChartVo> getSummaryListNoPage(@Param("params")Map<String, Object> params);


}
