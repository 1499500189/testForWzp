package io.renren.modules.milliondataexport.exportexcel;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.generator.dao.CrmWorkbenchDao;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author
 * @date 2022 年 02 月 21 日
 */
public class RunThread implements Runnable {
    private  HttpServletResponse response;
    private  Map<String, Object> params ;
    private  CrmWorkbenchDao crmWorkbenchDao;
    private  Integer i;
    private  CrmWorkbenchService crmWorkbenchService;
    public RunThread(Logger log) {
        this.log = log;
    }

    public RunThread(HttpServletResponse response, Map<String, Object> params, CrmWorkbenchDao crmWorkbenchDao, Integer i, CrmWorkbenchService crmWorkbenchService) {
        this.response = response;
        this.params = params;
        this.crmWorkbenchDao = crmWorkbenchDao;
        this.i = i;
        this.crmWorkbenchService =crmWorkbenchService;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */


    protected Logger log = LoggerFactory.getLogger(getClass());

    public  Object call(HttpServletResponse response, Map<String, Object> params, CrmWorkbenchDao crmWorkbenchDao,Integer i) throws Exception{

       return null;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //第几个线程
        long beforeTime = System.currentTimeMillis();
        QueryWrapper<ExcelWorkbenchDto> queryWrapper = new QueryWrapper<>();

        try {
            EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).sheet("工作台").doWrite(crmWorkbenchService.exportExcel(new SysUserEntity(), params,i));
          //  new ExportExcelListener<ExcelWorkbenchDto>(crmWorkbenchDao).exportExcel(response, i+"", ExcelWorkbenchDto.class,queryWrapper, crmWorkbenchDao, params, i);
        } catch (IOException e) {

          //
        }
        long afterTime = System.currentTimeMillis();
        log.error("耗时:{}", afterTime - beforeTime);


    }
}
