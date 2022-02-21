package io.renren.modules.milliondataexport.exportexcel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.generator.util.ExportExcelListener;
import io.renren.modules.generator.dao.CrmWorkbenchDao;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author
 * @date 2022 年 02 月 21 日
 */
public class GenaratorThread implements Callable {
    private  HttpServletResponse response;
    private  Map<String, Object> params ;
    private  CrmWorkbenchDao crmWorkbenchDao;
    private  Integer i;
    public GenaratorThread(Logger log) {
        this.log = log;
    }

    public GenaratorThread(HttpServletResponse response, Map<String, Object> params, CrmWorkbenchDao crmWorkbenchDao, Integer i) {
        this.response = response;
        this.params = params;
        this.crmWorkbenchDao = crmWorkbenchDao;
        this.i = i;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */


    protected Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public Object call() throws Exception {
        //第几个线程
        long beforeTime = System.currentTimeMillis();
        QueryWrapper<ExcelWorkbenchDto> queryWrapper = new QueryWrapper<>();

        try {
            new ExportExcelListener<ExcelWorkbenchDto>(crmWorkbenchDao).exportExcel(response, i+"", ExcelWorkbenchDto.class,
                    queryWrapper, crmWorkbenchDao, params, i);
        } catch (IOException e) {

            e.printStackTrace();
        }
        long afterTime = System.currentTimeMillis();
        log.error("耗时:{}", afterTime - beforeTime);

        return   i ;


    }
    public  Object call(HttpServletResponse response, Map<String, Object> params, CrmWorkbenchDao crmWorkbenchDao,Integer i) throws Exception{

       return null;
    }
}
