package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.annotation.SysLog;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.CrmWorkloadEntity;
import io.renren.modules.generator.service.CrmWorkloadService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-24 14:20:25
 */
@RestController
@RequestMapping("generator/crmworkload")
public class CrmWorkloadController extends AbstractController {
    @Autowired
    private CrmWorkloadService crmWorkloadService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:crmworkload:list")
    public R list(@RequestParam Map<String, Object> params){
      //  PageUtils page = crmWorkloadService.queryPage(params);
        IPage<CrmWorkloadEntity> cPage =  crmWorkloadService.selectWorkloadList(params,getUser());
        if (cPage == null) {
            cPage = new Page<>();
            PageUtils pageUtils = new PageUtils(cPage);
            return R.ok().put("page", pageUtils);
        }
        PageUtils pageUtils = new PageUtils(cPage);

        return R.ok().put("page", pageUtils);

       // return R.ok().put("page", page);
    }

    //获取到当前的登录用户
    @RequestMapping("/getUser")
    @RequiresPermissions("generator:crmworkload:list")
    public R getCurrentUser(){
        SysUserEntity user = getUser();
        return R.ok().put("username",user.getUsername());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:crmworkload:info")
    public R info(@PathVariable("id") Integer id){
	 //	CrmWorkloadEntity crmWorkload = crmWorkloadService.getById(id);
        CrmWorkloadEntity crmWorkload = crmWorkloadService.getWorkload(getUser(),id);
        return R.ok().put("crmWorkload", crmWorkload);
    }

    /**
     * 保存
     */
    @SysLog("工作量汇报保存")
    @RequestMapping("/save")
    @Deprecated
    @RequiresPermissions("generator:crmworkload:save")
    public R save(@RequestBody CrmWorkloadEntity crmWorkload){
        crmWorkload.setUserId(getUserId());
        crmWorkload.setCreateTime(new Date());

        for(int i = 0 ;i<500000;i++) {
            Date date = new Date(i);
            crmWorkload.setCreateTime(date);
            crmWorkload.setOperationDate(date);
            crmWorkloadService.save(crmWorkload);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("工作量汇报修改功能")
    @RequestMapping("/update")
    @RequiresPermissions("generator:crmworkload:update")
    public R update(@RequestBody CrmWorkloadEntity crmWorkload){
		crmWorkloadService.updateById(crmWorkload);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("工作量汇报删除功能")
    @RequestMapping("/delete")
    @RequiresPermissions("generator:crmworkload:delete")
    public R delete(@RequestBody Integer[] ids){
		crmWorkloadService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
