package io.renren.modules.generator.controller.excelexport;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.renren.QueryCondition;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.service.CrmWorkbenchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author
 * @date 2022 年 02 月 21 日
 */
public class ReadExifInfoThreadWebMultipleExcel implements Callable<Boolean> {
    private QueryCondition queryCondition;
    private CrmWorkbenchService crmWorkbenchService;
    private  int i;
    private  int num;
    private Map<String, Object> params;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private  HttpServletResponse response;
    private final ExcelWriter excelWriter;
    private  CountDownLatch latch;

    public ReadExifInfoThreadWebMultipleExcel(QueryCondition queryCondition, CrmWorkbenchService crmWorkbenchService, int i, int num, Map<String, Object> params, HttpServletResponse response,ExcelWriter excelWriter) {
        this.queryCondition = queryCondition;
        this.crmWorkbenchService = crmWorkbenchService;
        this.i = i;
        this.num = num;
        this.params =params;
        this.response =response;
            this.excelWriter =excelWriter;

    }

    public ReadExifInfoThreadWebMultipleExcel(QueryCondition queryCondition, CrmWorkbenchService crmWorkbenchService, int i, int num, Map<String, Object> params, HttpServletResponse response, ExcelWriter excelWriter, CountDownLatch latch) {
        this.queryCondition = queryCondition;
        this.crmWorkbenchService = crmWorkbenchService;
        this.i = i;
        this.num = num;
        this.params =params;
        this.response =response;
        this.excelWriter =excelWriter;
        this.latch=latch;

    }

    @Override
    public Boolean call(){

        try{
            long startTime = System.currentTimeMillis();
            List<ExcelWorkbenchDto> exifInfoList = null;
            //从数据库查询要写入excle的数据
            exifInfoList  = crmWorkbenchService.selectWorkbenchListPageLimit(i*100000, 100000,i*100000,params);
            long endTime=System.currentTimeMillis();
            long spendTime=endTime-startTime;
            logger.info(Thread.currentThread().getName()+"查询耗时："+spendTime);
            ServletOutputStream out = response.getOutputStream();
            synchronized(excelWriter){
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "信息表" + (i + 1)).build();
                excelWriter.write(exifInfoList, writeSheet);
            }



            long lastTime=System.currentTimeMillis();
            long  writeTime = lastTime-endTime;
            logger.info(Thread.currentThread().getName()+"写入耗时："+writeTime);

            return true;
        }catch (Exception e){
            e.getStackTrace();

        }


        return false;
    }
}
