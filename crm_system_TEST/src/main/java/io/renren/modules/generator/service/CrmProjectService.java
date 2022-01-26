package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.entity.vo.WorkbenchProjectVo;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 11:48:12
 */
public interface CrmProjectService extends IService<CrmProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<CrmProjectEntity> selectAllList(Map<String, Object> params);

    void saveProject(CrmProjectEntity crmProject);

    void updateByIdCrmProject(CrmProjectEntity crmProject);

    CrmProjectEntity getProject(Long projectId);

    boolean isIntegral(Long projectId);
     CrmCategoryEntity getCrmCategoryEntity(Long projectId) ;

    CrmCategoryEntity getCrmCategoryEntityByProjectName(String projectName);

    CrmProjectEntity getCrmProjectEntityByProjectName(String projectName);

    List<WorkbenchProjectVo> getProjectListUseWorkbench();

    WorkbenchProjectVo getProjectUseWorkbench(String projectName);
}

