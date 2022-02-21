package io.renren.modules.generator.controller.nowci;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.renren.QueryCondition;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.service.CrmWorkbenchService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class WriteExcel {
    public static void writeExcel(CrmWorkbenchService exportExcelservice, QueryCondition queryCondition, int exifInfoCount, String finalXlsxPath, Map<String, Object> params) {
        //每个sheet保存的数据量
        int num = 100000;
        ExcelWriter excelWriter = null;
        int corePoolSize = 10;
        int maximumPoolSize = 20;
        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<ReadExifInfoThread> tasks = new ArrayList<ReadExifInfoThread>();
        excelWriter = EasyExcel.write(finalXlsxPath, ExcelWorkbenchDto.class).build();
        //exifInfoCount 写入excel数据总量
        //pageCount 要写入sheet页数量。同分页
        //   int pageCount = exifInfoCount % num == 0 ? (exifInfoCount / num) : (exifInfoCount / num + 1);
        for (int i = 0; i < 11; i++) {
            ReadExifInfoThread readExifInfoThread = new ReadExifInfoThread(queryCondition, exportExcelservice, i, num, params);
            tasks.add(readExifInfoThread);
        }
        //用invokeAll方法提交任务，返回数据的顺序和tasks中的任务顺序一致，如果第一个线程查0-10000行数据，第二个线程查10000-10001行数据，
        //第二个线程大概率比第一个线程先执行完，但是futures中第一位数据是0-10000的数据。
        //[demo见：](https://blog.csdn.net/weixin_43614067/article/details/104983719)
        List<Future<List<ExcelWorkbenchDto>>> futures = null;
        try {
            futures = exector.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 11; i++) {
            List<ExcelWorkbenchDto> exifInfoList = null;
            try {
                exifInfoList = futures.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //   WriteSheet writeSheet = EasyExcel.writerSheet(i, "xxxx信息表" + (i + 1)).build();
            //     excelWriter.write(exifInfoList, writeSheet);
        }
        //  exector.shutdown();
        excelWriter.finish();
    }

    public static void writeExcelWeb(CrmWorkbenchService exportExcelservice, QueryCondition queryCondition, int exifInfoCount, String finalXlsxPath, Map<String, Object> params, HttpServletResponse response) {
        //每个sheet保存的数据量
        int num = 100000;
        ExcelWriter excelWriter = null;
        int corePoolSize = 10;
        int maximumPoolSize = 20;
        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<ReadExifInfoThreadWeb> tasks = new ArrayList<ReadExifInfoThreadWeb>();
        //  excelWriter = EasyExcel.write(finalXlsxPath, ExcelWorkbenchDto.class).build();
        //exifInfoCount 写入excel数据总量
        //pageCount 要写入sheet页数量。同分页
        //   int pageCount = exifInfoCount % num == 0 ? (exifInfoCount / num) : (exifInfoCount / num + 1);

        //用invokeAll方法提交任务，返回数据的顺序和tasks中的任务顺序一致，如果第一个线程查0-10000行数据，第二个线程查10000-10001行数据，
        //第二个线程大概率比第一个线程先执行完，但是futures中第一位数据是0-10000的数据。
        //[demo见：](https://blog.csdn.net/weixin_43614067/article/details/104983719)
        List<Future<List<ExcelWorkbenchDto>>> futures = null;
        try {

            for (int i = 0; i < 11; i++) {
                ReadExifInfoThreadWeb readExifInfoThread = new ReadExifInfoThreadWeb(queryCondition, exportExcelservice, i, num, params, response);
                tasks.add(readExifInfoThread);
            }
            futures = exector.invokeAll(tasks);
            try {
                excelWriter = EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).build();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "测试" + i).build();
                try {
                    excelWriter.write(futures.get(i).get(), writeSheet);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            //不关闭会打不开excel
            excelWriter.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.getStackTrace();
        }
        exector.shutdown();
        excelWriter.finish();

    }
}
