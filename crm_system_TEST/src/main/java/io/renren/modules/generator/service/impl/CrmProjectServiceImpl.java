package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.exception.RRException;
import io.renren.modules.employee.constant.ConstantIntegral;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.vo.WorkbenchProjectVo;
import io.renren.modules.generator.service.CrmCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CrmProjectDao;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.service.CrmProjectService;


@Service("crmProjectService")
public class CrmProjectServiceImpl extends ServiceImpl<CrmProjectDao, CrmProjectEntity> implements CrmProjectService {
    @Autowired
    private CrmCategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmProjectEntity> page = this.page(
                new Query<CrmProjectEntity>().getPage(params),
                new QueryWrapper<CrmProjectEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage<CrmProjectEntity> selectAllList(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get("page"));
        long limit = Long.parseLong((String) params.get("limit"));
        Page<CrmProjectEntity> iPage = new Page<>(page,limit);
        IPage<CrmProjectEntity> projectList =   baseMapper.selectAllList(iPage,params);
        return projectList;
    }
    public CrmProjectEntity getCrmProjectEntityByProjectName(String projectName) {
        QueryWrapper<CrmProjectEntity> projectWrapper = new QueryWrapper<>();
        projectWrapper.eq("project_name",projectName);
        return baseMapper.selectOne(projectWrapper);
    }

    @Override
    public List<WorkbenchProjectVo> getProjectListUseWorkbench() {
        QueryWrapper<CrmProjectEntity> projectWrapper = new QueryWrapper<>();
        projectWrapper.select("project_name","project_id","achievements","category_id");
        List<CrmProjectEntity> projectList = baseMapper.selectList(projectWrapper);
        ArrayList<WorkbenchProjectVo> workbenchProjectVos = new ArrayList<>();
        for (CrmProjectEntity crmProjectEntity : projectList) {
          //  log.error(crmProjectEntity.toString());
            WorkbenchProjectVo workbenchProjectVo = new WorkbenchProjectVo();
            BeanUtils.copyProperties(crmProjectEntity,workbenchProjectVo);
            Long categoryId = crmProjectEntity.getCategoryId();
            QueryWrapper<CrmCategoryEntity> categoryWrapper = new QueryWrapper<>();
            categoryWrapper.eq("category_id",categoryId);
            CrmCategoryEntity categoryEntity = categoryService.getOne(categoryWrapper);
            if (categoryEntity.getCategoryName().equals(ConstantIntegral.integral)){
                workbenchProjectVo.setIfIntegral(true);
                workbenchProjectVo.setTotalPoints(0);
            }else {
                workbenchProjectVo.setIfIntegral(false);
            }
            workbenchProjectVo.setNumberAchievements(crmProjectEntity.getAchievements());
            workbenchProjectVos.add(workbenchProjectVo);
         //   log.error(crmProjectEntity.toString());

        }
        return workbenchProjectVos;
    }

    @Override
    public WorkbenchProjectVo getProjectUseWorkbench(String projectName) {
        QueryWrapper<CrmProjectEntity> projectWrapper = new QueryWrapper<>();
        projectWrapper.select("project_name","project_id","achievements","category_id");
        projectWrapper.eq("project_name",projectName);
        CrmProjectEntity crmProjectEntity = baseMapper.selectOne(projectWrapper);


            WorkbenchProjectVo workbenchProjectVo = new WorkbenchProjectVo();
            BeanUtils.copyProperties(crmProjectEntity,workbenchProjectVo);
            Long categoryId = crmProjectEntity.getCategoryId();
            QueryWrapper<CrmCategoryEntity> categoryWrapper = new QueryWrapper<>();
            categoryWrapper.eq("category_id",categoryId);
            CrmCategoryEntity categoryEntity = categoryService.getOne(categoryWrapper);
            if (categoryEntity.getCategoryName().equals(ConstantIntegral.integral)){
                workbenchProjectVo.setIfIntegral(true);
                workbenchProjectVo.setTotalPoints(0);
            }else {
                workbenchProjectVo.setIfIntegral(false);
            }
            workbenchProjectVo.setNumberAchievements(crmProjectEntity.getAchievements());
            //   log.error(crmProjectEntity.toString());

        return workbenchProjectVo;
    }

    @Override
    public void saveProject(CrmProjectEntity crmProject) {
        String category = crmProject.getCategory();
        QueryWrapper<CrmCategoryEntity> cateWrapper = new QueryWrapper<>();
        cateWrapper.eq("category_name",category);
        CrmCategoryEntity categoryEntity = categoryService.getOne(cateWrapper);
        crmProject.setCategoryId(categoryEntity.getCategoryId());
        try{
            baseMapper.insert(crmProject);
        }catch (DuplicateKeyException e){
            throw  new RRException("项目名称重复");
        }

    }

    @Override
    public void updateByIdCrmProject(CrmProjectEntity crmProject) {
        String category = crmProject.getCategory();
        QueryWrapper<CrmCategoryEntity> crmCategoryEntityQueryWrapper = new QueryWrapper<>();
        crmCategoryEntityQueryWrapper.eq("category_name",category);
        CrmCategoryEntity categoryEntity = categoryService.getOne(crmCategoryEntityQueryWrapper);
        if (categoryEntity==null){
            throw  new RRException("项目类别不存在",407);
        }else {
            Long categoryId = categoryEntity.getCategoryId();
            crmProject.setCategoryId(categoryId);
           try{
               baseMapper.updateById(crmProject);
           }catch (DuplicateKeyException e){
               throw  new RRException("项目名称重复");
           }
        }
    }

    @Override
    public CrmProjectEntity getProject(Long projectId) {
        CrmProjectEntity crmProjectEntity = baseMapper.selectById(projectId);
        Long categoryId = crmProjectEntity.getCategoryId();
        QueryWrapper<CrmCategoryEntity> crmCategoryEntityQueryWrapper = new QueryWrapper<>();
        crmCategoryEntityQueryWrapper.eq("category_id",categoryId);
        CrmCategoryEntity categoryEntity = categoryService.getOne(crmCategoryEntityQueryWrapper);

        crmProjectEntity.setCategory(categoryEntity.getCategoryName());

        return crmProjectEntity;
    }

    @Override
    public boolean isIntegral(Long projectId) {
        CrmCategoryEntity categoryEntity = getCrmCategoryEntity(projectId);
        String categoryName = categoryEntity.getCategoryName();
        if (categoryName.equals("积分")){
            return  true;
        }else {
            return false;
        }
    }

    public CrmCategoryEntity getCrmCategoryEntity(Long projectId) {
        if (projectId==null){
            throw  new RRException("请填写项目名称",399);
        }
        CrmProjectEntity projectEntity = baseMapper.selectById(projectId);
        Long categoryId = projectEntity.getCategoryId();
        QueryWrapper<CrmCategoryEntity> crmCategoryEntityQueryWrapper = new QueryWrapper<>();
        crmCategoryEntityQueryWrapper.eq("category_id",categoryId);
        return categoryService.getOne(crmCategoryEntityQueryWrapper);
    }

    @Override
    public CrmCategoryEntity getCrmCategoryEntityByProjectName(String projectName) {
        if (projectName==null){
            throw  new RRException("请填写项目名称",399);
        }
        CrmProjectEntity crmProjectEntity = this.getCrmProjectEntityByProjectName(projectName);
       // QueryWrapper<CrmProjectEntity> projectWrapper = new QueryWrapper<>();
      //  projectWrapper.eq("project_name",projectName);
      //  CrmProjectEntity projectEntity = baseMapper.selectOne(projectWrapper);
        //CrmProjectEntity projectEntity = baseMapper.selectById(projectId);
        Long categoryId = crmProjectEntity.getCategoryId();
        QueryWrapper<CrmCategoryEntity> crmCategoryEntityQueryWrapper = new QueryWrapper<>();
        crmCategoryEntityQueryWrapper.eq("category_id",categoryId);
        return categoryService.getOne(crmCategoryEntityQueryWrapper);
    }

}
