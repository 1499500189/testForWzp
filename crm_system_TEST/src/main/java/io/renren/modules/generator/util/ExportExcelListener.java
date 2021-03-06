package io.renren.modules.generator.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.generator.dao.CrmWorkbenchDao;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2022 年 02 月 18 日
 */
public class ExportExcelListener<T> {
    protected Logger log = LoggerFactory.getLogger(getClass());

    private BaseMapper baseMapper;

    public ExportExcelListener(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    private static final String DATA_FORMAT = "yyyy-MM-dd-HH-mm-ss";

    private static final String CHARACTER = "UTF-8";

    private static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=utf-8";

    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    private static final String CACHE_CONTROL = "Cache-Control";

    private static final String NO_STORE = "no-store";

    private static final String MAX_AGE = "max-age=0";

    private static final Integer PAGE_SIZE = 100000;

    //批量执行
    public void exportExcel(HttpServletResponse response, String sheetName, Class<T> pojoClass,
                            QueryWrapper<T> queryWrapper, CrmWorkbenchDao crmWorkbenchDao, Map<String,Object> params) throws IOException {


        SimpleDateFormat format = new SimpleDateFormat(DATA_FORMAT);
        String nowTime = format.format(new Date());

        StringBuffer bf = new StringBuffer();
        String fileName = bf.append(sheetName)
                .append(nowTime)
                .append(".xlsx")
                .toString();

        ExcelWriter excelWriter = null;
        ServletOutputStream out = response.getOutputStream();
        //设置字符集为utf-8
        response.setCharacterEncoding(CHARACTER);
        response.setContentType(CONTENT_TYPE);
        //通知浏览器服务器发送的数据格式
        response.setHeader(CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, CHARACTER));

        //发送一个报头，告诉浏览器当前页面不进行缓存，每次访问的时间必须从服务器上读取最新的数据
        response.setHeader(CACHE_CONTROL, NO_STORE);
        response.addHeader(CACHE_CONTROL, MAX_AGE);
        // 这里 需要指定写用哪个class去写
        excelWriter = EasyExcel.write(out, pojoClass).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
       // Integer number = baseMapper.selectCount(queryWrapper);
        //导出所有，使用不分页的方法
      //  List<CrmWorkbenchEntity> records = crmWorkbenchDao.selectColumn(params);

        Long aLong = crmWorkbenchDao.selectColumnCount(params);
        int pageNumber = (int) Math.ceil((double) aLong / (double) PAGE_SIZE);    //分页条数看情况
       log.error(pageNumber+"");
       log.error(PAGE_SIZE+"");
        // 去调用写入,根据数据库分页的总的页数来
        for (int i = 1; i <= pageNumber; i++) {
            //先定义一个空集合每次循环使他变成null减少内存的占用
            List<T> pageCity = null;
            Page<T> page = new Page<>(i, PAGE_SIZE);
            log.error(page.toString());
            Page<T> cityIPage = (Page<T>) crmWorkbenchDao.selectWorkbenchList(page, params);
            ArrayList<ExcelWorkbenchDto> excelDictDTOList = new ArrayList<>();



            List<T> records = cityIPage.getRecords();
            records.forEach(c -> {
                ExcelWorkbenchDto excelTransactionRecordDTO = new ExcelWorkbenchDto();
            BeanUtils.copyProperties(c, excelTransactionRecordDTO);
                excelDictDTOList.add(excelTransactionRecordDTO);
               });


           // pageCity = cityIPage.getRecords();
            excelWriter.write(excelDictDTOList , writeSheet);
            excelDictDTOList.clear();
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
        out.flush();
    }
//多线程


    public void exportExcel(HttpServletResponse response, String sheetName, Class<T> pojoClass,
                            QueryWrapper<T> queryWrapper, CrmWorkbenchDao crmWorkbenchDao, Map<String,Object> params
     ,Integer nowThread) throws IOException {


        SimpleDateFormat format = new SimpleDateFormat(DATA_FORMAT);
        String nowTime = format.format(new Date());

        StringBuffer bf = new StringBuffer();
        String fileName = bf.append(sheetName)
                .append(nowTime)
                .append(".xlsx")
                .toString();

        ExcelWriter excelWriter = null;
        ServletOutputStream out = response.getOutputStream();
        //设置字符集为utf-8
/*        response.setCharacterEncoding(CHARACTER);
        response.setContentType(CONTENT_TYPE);
        //通知浏览器服务器发送的数据格式
        response.setHeader(CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, CHARACTER));*/

        //发送一个报头，告诉浏览器当前页面不进行缓存，每次访问的时间必须从服务器上读取最新的数据
     /*   response.setHeader(CACHE_CONTROL, NO_STORE);
        response.addHeader(CACHE_CONTROL, MAX_AGE);*/
        // 这里 需要指定写用哪个class去写
        excelWriter = EasyExcel.write(out, pojoClass).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
        // Integer number = baseMapper.selectCount(queryWrapper);
        //导出所有，使用不分页的方法
        //  List<CrmWorkbenchEntity> records = crmWorkbenchDao.selectColumn(params);

        // 去调用写入,根据数据库分页的总的页数来
       /* for (int i = 1; i <= pageNumber; i++) {*/
            //先定义一个空集合每次循环使他变成null减少内存的占用
            List<T> pageCity = null;
            Page<T> page = new Page<>(nowThread, PAGE_SIZE);
            log.error(page.toString());
            Page<T> cityIPage = (Page<T>) crmWorkbenchDao.selectWorkbenchList(page, params);
            ArrayList<ExcelWorkbenchDto> excelDictDTOList = new ArrayList<>();



            List<T> records = cityIPage.getRecords();
            records.forEach(c -> {
                ExcelWorkbenchDto excelTransactionRecordDTO = new ExcelWorkbenchDto();
                BeanUtils.copyProperties(c, excelTransactionRecordDTO);
                excelDictDTOList.add(excelTransactionRecordDTO);
            });

            excelWriter.write(excelDictDTOList , writeSheet);
            excelDictDTOList.clear();

        // 千万别忘记finish 会帮忙关闭流
       // excelWriter.finish();
          out.flush();
    }

}
