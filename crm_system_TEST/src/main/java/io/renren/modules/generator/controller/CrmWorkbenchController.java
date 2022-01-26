package io.renren.modules.generator.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import cn.hutool.core.date.DateTime;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.vo.WorkbenchProjectVo;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 15:00:52
 */
@RestController
@RequestMapping("generator/crmworkbench")
public class CrmWorkbenchController extends AbstractController {
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;
    @Autowired
    private CrmProjectService crmProjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:crmworkbench:list")
    public R list(@RequestParam Map<String, Object> params){
        IPage<CrmWorkbenchEntity> cPage =  crmWorkbenchService.selectWorkbenchList(params,getUser());
        if (cPage == null) {
            cPage = new Page<>();
            PageUtils pageUtils = new PageUtils(cPage);
            return R.ok().put("page", pageUtils);
        }
        PageUtils pageUtils = new PageUtils(cPage);

        return R.ok().put("page", pageUtils);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:crmworkbench:info")
    public R info(@PathVariable("id") Long id){
		CrmWorkbenchEntity crmWorkbench = crmWorkbenchService.getWorkbench(id);
        return R.ok().put("crmWorkbench", crmWorkbench);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @SysLog("工作台保存信息")
    @RequiresPermissions("generator:crmworkbench:save")
    public R save(@RequestBody CrmWorkbenchEntity crmWorkbench){
        logger.info(crmWorkbench.toString());
        crmWorkbench.setCreateTime(new Date());
        crmWorkbench.setUserId(getUserId());
        crmWorkbench.setIsTrue("是");
		//crmWorkbenchService.save(crmWorkbench);
        crmWorkbenchService.saveWorkbenchEntity(crmWorkbench,getUser());

        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("工作台修改信息")
    @RequestMapping("/update")
    @RequiresPermissions("generator:crmworkbench:update")
    public R update(@RequestBody CrmWorkbenchEntity crmWorkbench){
        if (crmWorkbench.getTelephone().contains("*")){
            crmWorkbench.setTelephone(null);
        }
        crmWorkbenchService.updateWorkbenchEntity(crmWorkbench,getUser());
		//crmWorkbenchService.updateById(crmWorkbench);

        return R.ok();
    }
    //获取到当前的登录用户
    @RequestMapping("/getUser")
    @RequiresPermissions("generator:crmworkbench:list")
    public R getCurrentUser(){
        SysUserEntity user = getUser();
        return R.ok().put("username",user.getUsername());
    }

    /**
     * 删除
     */
    @SysLog("工作台删除文件")
    @RequestMapping("/delete")
    @RequiresPermissions("generator:crmworkbench:delete")
    public R delete(@RequestBody Integer[] ids){
		crmWorkbenchService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
    /**
     * 项目列表
     */
    @RequestMapping("/projectList")
    @RequiresPermissions("generator:crmworkbench:list")
    public R projectList(){

       List<WorkbenchProjectVo> workbenchProjectVos = crmProjectService.getProjectListUseWorkbench();
        return R.ok().put("projectList", workbenchProjectVos);
    }
    /**
     * 获取项目的属性
     */
    @RequestMapping("/getProject")
    @RequiresPermissions("generator:crmworkbench:list")
    public R save(String projectName){

        WorkbenchProjectVo workbenchProjectVo = crmProjectService.getProjectUseWorkbench(projectName);
        return R.ok().put("project",workbenchProjectVo);
    }
    /**
     * 查看是否是积分的分类
     */
    @RequestMapping("/isIntegral")
    @RequiresPermissions("generator:crmworkbench:list")
    public R isIntegral(@RequestBody Long projectId){
        boolean isIntegral = crmProjectService.isIntegral(projectId);
        return  R.ok().put("isIntegral",isIntegral);
    }

    //导出文件
    @RequestMapping("/exportExcel")
    @SysLog("工作台导出文件")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
            if (params.get("startTime") == null || params.get("endTime") == null) {
            throw new RRException("时间间隔不能为空", 403);
            }
            Date startTime = DateTime.of((String) params.get("startTime"), "yyyy-MM-dd HH:mm:ss"); //获取到开始的时间
            Date endTime = DateTime.of((String) params.get("endTime"), "yyyy-MM-dd HH:mm:ss");  //获取到结束的时间
            if ((endTime.getTime() - startTime.getTime()) > 1000 * 60 * 60 * 24 * 31L) {  //转换成毫秒 ，减去31天的毫秒数 ，之后就是
                throw new RRException("时间的间隔过大", 403);
            }

        //导出excel
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("excel", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).sheet("工作台").doWrite(crmWorkbenchService.exportExcel(getUser(), params));
            //  MultipartFile file =     crmTransactionRecordService.exportExcel(getUser(),params);
        } catch (IOException e) {
            throw new RRException("数据导出失败", 403);
        }

    }

}
