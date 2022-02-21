package io.renren.modules.generator.controller.nowci;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
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

/**
 * @author
 * @date 2022 年 02 月 21 日
 */
public class ReadExifInfoThreadWeb  implements Callable<List<ExcelWorkbenchDto>> {
    private QueryCondition queryCondition;
    private CrmWorkbenchService crmWorkbenchService;
    private  int i;
    private  int num;
    private Map<String, Object> params;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private  HttpServletResponse response;

    public ReadExifInfoThreadWeb(QueryCondition queryCondition, CrmWorkbenchService crmWorkbenchService, int i, int num, Map<String, Object> params, HttpServletResponse response) {
        this.queryCondition = queryCondition;
        this.crmWorkbenchService = crmWorkbenchService;
        this.i = i;
        this.num = num;
        this.params =params;
        this.response =response;
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



           // ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).build();
       /*     WriteSheet writeSheet = EasyExcel.writerSheet(i, "xxxx信息表" + (i + 1)).build();
            excelWriter.write(exifInfoList, writeSheet);
            excelWriter.finish();
*/

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
