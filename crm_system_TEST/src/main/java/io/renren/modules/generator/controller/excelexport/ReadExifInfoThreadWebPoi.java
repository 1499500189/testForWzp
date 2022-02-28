package io.renren.modules.generator.controller.excelexport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.QueryCondition;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmWorkbenchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author
 * @date 2022 年 02 月 21 日
 */
public class ReadExifInfoThreadWebPoi implements Callable<List<ExcelWorkbenchDto>> {
    private QueryCondition queryCondition;
    private CrmWorkbenchService crmWorkbenchService;
    private  int i;
    private  int num;
    private Map<String, Object> params;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private  HttpServletResponse response;
    private  CountDownLatch latch;

    public ReadExifInfoThreadWebPoi(QueryCondition queryCondition, CrmWorkbenchService crmWorkbenchService, int i, int num, Map<String, Object> params, HttpServletResponse response) {
        this.queryCondition = queryCondition;
        this.crmWorkbenchService = crmWorkbenchService;
        this.i = i;
        this.num = num;
        this.params =params;
        this.response =response;
    }

    public ReadExifInfoThreadWebPoi(QueryCondition queryCondition, CrmWorkbenchService crmWorkbenchService, int i, int num, Map<String, Object> params, HttpServletResponse response, CountDownLatch latch) {
        this.queryCondition = queryCondition;
        this.crmWorkbenchService = crmWorkbenchService;
        this.i = i;
        this.num = num;
        this.params =params;
        this.response =response;
        this.latch=latch;
    }

    @Override
    public List<ExcelWorkbenchDto> call(){

        try{
            long startTime = System.currentTimeMillis();
            List<ExcelWorkbenchDto> exifInfoList = null;


            exifInfoList  = crmWorkbenchService.selectWorkbenchListPageLimit(i*100000, 100000,i*100000,params);
            long endTime=System.currentTimeMillis();
            long spendTime=endTime-startTime;
            logger.info(Thread.currentThread().getName()+"查询耗时："+spendTime);

            return exifInfoList;
        }catch (Exception e){
            e.getStackTrace();

        }


        return null;
    }
}
