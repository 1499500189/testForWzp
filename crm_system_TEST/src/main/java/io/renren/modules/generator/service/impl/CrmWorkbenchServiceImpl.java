package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.employee.constant.ConstantRoles;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.generator.entity.dto.ExcelSummaryStatisticsDto;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.entity.dto.SummaryStatisticsExcelDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmCategoryService;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CrmWorkbenchDao;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.service.CrmWorkbenchService;


@Service("crmWorkbenchService")
public class CrmWorkbenchServiceImpl extends ServiceImpl<CrmWorkbenchDao, CrmWorkbenchEntity> implements CrmWorkbenchService {
    @Autowired
    private CrmCategoryService crmCategoryService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CrmProjectService crmProjectService;
    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmWorkbenchEntity> page = this.page(
                new Query<CrmWorkbenchEntity>().getPage(params),
                new QueryWrapper<CrmWorkbenchEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage<CrmWorkbenchEntity> selectWorkbenchList(Map<String, Object> params, SysUserEntity currentUser) {


        String roleName = sysUserService.getCurrentUserRoleName(currentUser);
        if (!roleName.equals(ConstantRoles.OW)) {
            EmployeeInfoEntity employeeInfoEntity = employeeInfoService.queryUserId(currentUser.getUserId());
            QueryWrapper<EmployeeInfoEntity> emWrapper = new QueryWrapper<>();
            emWrapper.likeRight("codes", employeeInfoEntity.getCodes());
            List<EmployeeInfoEntity> emList = employeeInfoService.list(emWrapper);
            params.put("emList", emList);
        }
        long page = Long.parseLong((String) params.get("page"));
        long limit = Long.parseLong((String) params.get("limit"));
        Page<CrmWorkbenchEntity> iPage = new Page<>(page, limit);
        IPage<CrmWorkbenchEntity> workbenchList = baseMapper.selectWorkbenchList(iPage, params);
        List<CrmWorkbenchEntity> records = workbenchList.getRecords();
        for (CrmWorkbenchEntity record : records) {
            String telephone = record.getTelephone();
            if (telephone.length() >= 7) {
                record.setTelephone(telephone.substring(0, 3) + "****" + telephone.substring(7));
            }
        }

        return workbenchList;
    }



    @Override
    public CrmWorkbenchEntity getWorkbench(Long id) {
        CrmWorkbenchEntity crmWorkbenchEntity = baseMapper.selectById(id);
        Long categoryId = crmWorkbenchEntity.getCategoryId();
        QueryWrapper<CrmCategoryEntity> categoryWrapper = new QueryWrapper<>();
        categoryWrapper.eq("category_id", categoryId);
        CrmCategoryEntity categoryEntity = crmCategoryService.getOne(categoryWrapper);
        if (null != categoryEntity) {
            crmWorkbenchEntity.setCategoryName(categoryEntity.getCategoryName());
        }


        Long projectId = crmWorkbenchEntity.getProjectId();
        QueryWrapper<CrmProjectEntity> projectWrapper = new QueryWrapper<>();
        projectWrapper.eq("project_id", projectId);
        CrmProjectEntity projectEntity = crmProjectService.getOne(projectWrapper);
        if (null != projectEntity) {
            crmWorkbenchEntity.setProjectName(projectEntity.getProjectName());
        }

        Long userId = crmWorkbenchEntity.getUserId();
        QueryWrapper<SysUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("user_id", userId);
        SysUserEntity userEntity = sysUserService.getOne(userWrapper);
        if (null != userEntity) {
            crmWorkbenchEntity.setUsername(userEntity.getUsername());
        }

        String telephone = crmWorkbenchEntity.getTelephone();
        if (telephone.length() >= 7) {
            crmWorkbenchEntity.setTelephone(telephone.substring(0, 3) + "****" + telephone.substring(7));
        }


        return crmWorkbenchEntity;
    }

    @Override
    public IPage<ChartVo> getSummaryList(Map<String, Object> params, SysUserEntity currentUser) {
        //分页参数
        long curPage = 1;
        long limit = 10;
        if ((String)params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }
        Page<CrmProjectEntity> iPage = new Page<>(curPage,limit);
        IPage<ChartVo> c = baseMapper.getSummaryList(iPage,params);

        return c;
    }
    @Override
    public List<ExcelWorkbenchDto> exportExcel(SysUserEntity user, Map<String, Object> params) {

        //导出所有，使用不分页的方法
        List<CrmWorkbenchEntity> records = baseMapper.selectColumn(params);
        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
        ArrayList<ExcelWorkbenchDto> excelDictDTOList = new ArrayList<>(records.size());
        records.forEach(c -> {
            ExcelWorkbenchDto excelTransactionRecordDTO = new ExcelWorkbenchDto();
            BeanUtils.copyProperties(c, excelTransactionRecordDTO);
            excelDictDTOList.add(excelTransactionRecordDTO);
        });
        return excelDictDTOList;
    }
    @Override
    public List<ExcelSummaryStatisticsDto> exportSummaryStatisticsExcel(SysUserEntity user, Map<String, Object> params) {
        //导出所有，使用不分页的方法
        List<ChartVo> records  = baseMapper.getSummaryListNoPage(params);
        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
        List<ExcelSummaryStatisticsDto> excelSummaryStatisticsDtos = new ArrayList<>(records.size());
        records.forEach(c -> {
            ExcelSummaryStatisticsDto excelTransactionRecordDTO = new ExcelSummaryStatisticsDto();
            BeanUtils.copyProperties(c, excelTransactionRecordDTO);
            excelSummaryStatisticsDtos.add(excelTransactionRecordDTO);
        });
        return excelSummaryStatisticsDtos;
    }

    @Override
    public void updateWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity currentUser) {
       // log.error(crmWorkbench.toString());
        String roleName = sysUserService.getCurrentUserRoleName(currentUser);
        if (roleName.equals(ConstantRoles.executiveDirector) || roleName.equals(ConstantRoles.staff)) {
            throw new RRException("不能修改", 399);
        }
      //  Long projectId = crmWorkbench.getProjectId();
        //CrmCategoryEntity crmCategoryEntity = crmProjectService.getCrmCategoryEntity(projectId);
        String projectName = crmWorkbench.getProjectName();
        CrmProjectEntity crmProjectEntity = crmProjectService.getCrmProjectEntityByProjectName(projectName);
        CrmCategoryEntity crmCategoryEntity = crmProjectService.getCrmCategoryEntityByProjectName(projectName);
        crmWorkbench.setCategoryId(crmCategoryEntity.getCategoryId());
        crmWorkbench.setProjectId(crmProjectEntity.getProjectId());

        baseMapper.updateById(crmWorkbench);
    }



    @Override
    public void saveWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user) {
       // Long projectId = crmWorkbench.getProjectId();
        String projectName = crmWorkbench.getProjectName();
        CrmProjectEntity crmProjectEntity = crmProjectService.getCrmProjectEntityByProjectName(projectName);
        CrmCategoryEntity crmCategoryEntity = crmProjectService.getCrmCategoryEntityByProjectName(projectName);
        crmWorkbench.setCategoryId(crmCategoryEntity.getCategoryId());
        crmWorkbench.setProjectId(crmProjectEntity.getProjectId());

        try{
            baseMapper.insert(crmWorkbench);
        }catch (DataIntegrityViolationException e) {
            throw new RRException("信息字段长度过长", 500);
        }


    }



}
