package io.renren.modules.generator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.annotation.SysLog;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.service.CrmCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 11:48:12
 */
@RestController
@RequestMapping("/generator/crmproject")
public class CrmProjectController {
    @Autowired
    private CrmProjectService crmProjectService;
    @Autowired
    private CrmCategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:crmproject:list")
    public R list(@RequestParam Map<String, Object> params){
        /*PageUtils page = crmProjectService.queryPage(params);*/
        IPage<CrmProjectEntity> cPage =  crmProjectService.selectAllList(params);
        if (cPage == null) {
            cPage = new Page<>();
            PageUtils pageUtils = new PageUtils(cPage);
            return R.ok().put("page", pageUtils);
        }
        PageUtils pageUtils = new PageUtils(cPage);
        return R.ok().put("page", pageUtils);
    }
    /**
     * 列表
     */
    @RequestMapping("/categoryList")
    @RequiresPermissions("generator:crmproject:list")
    public R categoryList(){
        List<CrmCategoryEntity> categoryList = categoryService.list();
/*        ArrayList<String> categoryList = new ArrayList<>();
        for (CrmCategoryEntity c: list) {
            categoryList.add(c.getCategoryName());

        }*/
        return R.ok().put("categoryList", categoryList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{projectId}")
    @RequiresPermissions("generator:crmproject:info")
    public R info(@PathVariable("projectId") Long projectId){
        CrmProjectEntity crmProject = crmProjectService.getProject(projectId);
	//CrmProjectEntity crmProject = crmProjectService.getById(projectId);
      //  String category = crmProject.getCategory();
        return R.ok().put("crmProject", crmProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @SysLog("保存项目")
    @RequiresPermissions("generator:crmproject:save")
    public R save(@RequestBody CrmProjectEntity crmProject){
		//crmProjectService.save(crmProject);
        crmProjectService.saveProject(crmProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:crmproject:update")
    public R update(@RequestBody CrmProjectEntity crmProject){
	 //	crmProjectService.updateById(crmProject);
		crmProjectService.updateByIdCrmProject(crmProject);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除项目")
    @RequiresPermissions("generator:crmproject:delete")
    public R delete(@RequestBody Integer[] projectIds){
		crmProjectService.removeByIds(Arrays.asList(projectIds));

        return R.ok();
    }

}
