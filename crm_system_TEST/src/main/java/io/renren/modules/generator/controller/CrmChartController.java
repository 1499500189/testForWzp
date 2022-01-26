package io.renren.modules.generator.controller;

import cn.hutool.core.date.DateTime;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.generator.entity.dto.ExcelSummaryStatisticsDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-15 11:20:46
 */
@RestController
@RequestMapping("/generator/chart")
@Api(tags = "汇总统计")
public class CrmChartController extends AbstractController {
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;



    /**
     * 获取到图标的展示列表
     */
    @GetMapping("/list")
    @RequiresPermissions("generator:chart:list")
    @ApiOperation("获取到图表信息")
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();

       // List<ChartVo> chartList = crmWorkbenchService.getSummaryList(params, user);
       // return R.ok().put("chartList", chartList);

        IPage<ChartVo> cPage =  crmWorkbenchService.getSummaryList(params,user);
        if (cPage == null) {
            cPage = new Page<>();
            PageUtils pageUtils = new PageUtils(cPage);
            return R.ok().put("page", pageUtils);
        }
        PageUtils pageUtils = new PageUtils(cPage);
        return R.ok().put("page", pageUtils);
    }

    //导出文件
    @GetMapping("/exportExcel")
    //    @SysLog("统计汇总导出文件")   gson过程中报错
    @RequiresPermissions("generator:chart:exportexcel")
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
            EasyExcel.write(response.getOutputStream(), ExcelSummaryStatisticsDto.class).sheet("汇总统计").doWrite(crmWorkbenchService.exportSummaryStatisticsExcel(getUser(), params));
        } catch (IOException e) {
            throw new RRException("数据导出失败", 403);
        }

    }

  /*  @ApiOperation("获取到所有的员工列表")
    @GetMapping("/getAllEmployeeList")
    public R getAllEmployeeList() {
        List<EmployeeInfoEntity> employeeList = employeeInfoService.getNextLevelAllEmployeeList(getUser());
        List<DictionariesVO> showType = new ArrayList<DictionariesVO>();
        showType.add(new DictionariesVO("1", "按个人"));
        showType.add(new DictionariesVO("2", "按小组"));
        showType.add(new DictionariesVO("3", "按部门"));
        return R.ok().put("employeeList", employeeList).put("showType", showType);
    }*/

}
