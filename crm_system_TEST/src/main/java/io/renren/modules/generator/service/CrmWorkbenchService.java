package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.dto.ExcelSummaryStatisticsDto;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.dto.SummaryStatisticsExcelDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.sys.entity.SysUserEntity;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpServletResponse;
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

    Collection<?> exportExcel(SysUserEntity user, Map<String, Object> params,Integer integer);
    void exportExcel(HttpServletResponse httpServletResponse, Map<String, Object> params);
    CrmWorkbenchEntity getWorkbench(Long id);



    Collection<?> exportSummaryStatisticsExcel(SysUserEntity user, Map<String, Object> params);

    void updateWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user);

    void saveWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user);

    IPage<ChartVo> getSummaryList(Map<String, Object> params, SysUserEntity user);

    void doSomething(CrmWorkbenchEntity crmWorkbench, SysUserEntity user) throws InterruptedException;

    void insert();

    void exportExcelPeople(HttpServletResponse response, Map<String, Object> params);

    List<ExcelWorkbenchDto> getSummaryList(Page<ChartVo> chartVoPage, Map<String, Object> params);

    List<ExcelWorkbenchDto> selectWorkbenchListPageLimit(Integer i, Integer i1);
}

