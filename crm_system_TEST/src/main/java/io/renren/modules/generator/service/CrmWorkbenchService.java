package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.dto.ExcelSummaryStatisticsDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 15:00:52
 */
public interface CrmWorkbenchService extends IService<CrmWorkbenchEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<CrmWorkbenchEntity> selectWorkbenchList(Map<String, Object> params,SysUserEntity currentUser);

    Collection<?> exportExcel(SysUserEntity user, Map<String, Object> params);

    CrmWorkbenchEntity getWorkbench(Long id);



    Collection<?> exportSummaryStatisticsExcel(SysUserEntity user, Map<String, Object> params);

    void updateWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user);

    void saveWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user);

    IPage<ChartVo> getSummaryList(Map<String, Object> params, SysUserEntity user);
}

