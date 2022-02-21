package io.renren.modules.generator.controller.nowci;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.QueryCondition;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.dto.SummaryStatisticsExcelDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmWorkbenchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ReadExifInfoThread implements Callable<List<ExcelWorkbenchDto>> {
    private QueryCondition queryCondition;
    private CrmWorkbenchService crmWorkbenchService;
    private  int i;
    private  int num;
    private     Map<String, Object> params;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public ReadExifInfoThread(QueryCondition queryCondition, CrmWorkbenchService crmWorkbenchService, int i, int num, Map<String, Object> params) {
        this.queryCondition = queryCondition;
        this.crmWorkbenchService = crmWorkbenchService;
        this.i = i;
        this.num = num;
        this.params =params;
    }
    @Override
    public List<ExcelWorkbenchDto> call(){

        try{
            long startTime = System.currentTimeMillis();
            List<ExcelWorkbenchDto> exifInfoList = null;
            //从数据库查询要写入excle的数据
            Page<ChartVo> chartVoPage = new Page<>(i, 100000);
            //  exifInfoList = crmWorkbenchService.getSummaryList(chartVoPage,params);
            exifInfoList  = crmWorkbenchService.selectWorkbenchListPageLimit(i, 100000);
            long endTime=System.currentTimeMillis();
            long spendTime=endTime-startTime;
            logger.info(Thread.currentThread().getName()+"查询耗时："+spendTime);

            String finalXlsxPath =i+".xlsx";
            ExcelWriter excelWriter = EasyExcel.write(finalXlsxPath, ExcelWorkbenchDto.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(i, "xxxx信息表" + (i + 1)).build();
            excelWriter.write(exifInfoList, writeSheet);
            excelWriter.finish();
            long lastTime=System.currentTimeMillis();
            long  ennTime = lastTime-endTime;
            logger.info(Thread.currentThread().getName()+"查询耗时："+ennTime);
            return exifInfoList;
        }catch (Exception e){
            e.getStackTrace();

        }


        return null;
    }

}
