package io.renren.modules.generator.controller.excelexport;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.renren.QueryCondition;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.service.CrmWorkbenchService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class WriteExcel {
    public static void writeExcel(CrmWorkbenchService exportExcelservice, QueryCondition queryCondition, int exifInfoCount, String finalXlsxPath, Map<String, Object> params) {
        //每个sheet保存的数据量
        int num = 100000;
        ExcelWriter excelWriter = null;
        int corePoolSize = 11;
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
        //  exector.shutdown();
        excelWriter.finish();
    }

    public static void writeExcelWeb(CrmWorkbenchService exportExcelservice, QueryCondition queryCondition, int exifInfoCount, String finalXlsxPath, Map<String, Object> params, HttpServletResponse response) throws IOException {

        long start=System.currentTimeMillis();
        //每个sheet保存的数据量
        int num = 100000;
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).build();;
        int corePoolSize = 11;
        int maximumPoolSize = 20;

        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<ReadExifInfoThreadWeb> tasks = new ArrayList<ReadExifInfoThreadWeb>();


        List<Future<List<ExcelWorkbenchDto>>> futures = null;
        try {

            for (int i = 0; i < corePoolSize; i++) {
                ReadExifInfoThreadWeb readExifInfoThread = new ReadExifInfoThreadWeb(queryCondition, exportExcelservice, i, num, params, response);
                tasks.add(readExifInfoThread);
            }
            futures= exector.invokeAll(tasks);


            System.out.println("执行等待"+(System.currentTimeMillis()-start));
            WriteSheet writeSheet = EasyExcel.writerSheet(2,"测试").build();

            for (int i = 0; i < corePoolSize; i++) {

                   final   List<ExcelWorkbenchDto> lst=futures.get(i).get();
                excelWriter.write(lst, writeSheet);
//                    Executors.newFixedThreadPool(4).execute(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });

            }
            excelWriter.finish();
            exector.shutdown();

/*
            //不关闭会打不开excel
            excelWriter.finish();*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }



    }

    public static void writeExcelWebEx(CrmWorkbenchService exportExcelservice, QueryCondition queryCondition, int exifInfoCount, String finalXlsxPath, Map<String, Object> params, HttpServletResponse response) {


        //每个sheet保存的数据量
        int num = 100000;
        ExcelWriter excelWriter = null;
        int corePoolSize = 11;
        int maximumPoolSize = 20;
        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<ReadExifInfoThreadWebMultipleExcel> tasks = new ArrayList<ReadExifInfoThreadWebMultipleExcel>();
        List<Future<Boolean>> futures = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < corePoolSize; i++) {
                ReadExifInfoThreadWebMultipleExcel readExifInfoThread = new ReadExifInfoThreadWebMultipleExcel(queryCondition, exportExcelservice, i, num, params, response, excelWriter);
                tasks.add(readExifInfoThread);
            }
            futures = exector.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }

        exector.shutdown();

        excelWriter.finish();


    }

    public static void xss(CrmWorkbenchService crmWorkbenchService, Object o, int current, String ycy, Map<String, Object> params, HttpServletResponse response) {
        //定义一个常量，也就是sheet页的数量，等同于线程池里循环调用的次数。
       final  int sheetDealCount = 12;

        //定义一个CountDownLatch，它的作用可以理解为集齐七龙珠召唤神龙。
        //实际本例中我们必须要等线程池的所有线程执行完写数据的任务最后执行write操作。
        //在count到0之前，主线程将一直await状态。
        final CountDownLatch countDownLatch = new CountDownLatch(sheetDealCount);
        long startTime = System.currentTimeMillis();
        ArrayList<SXSSFSheet> swList = new ArrayList<>();
        //我们定义一个窗口是100000大小的SXSSFWorkbook
        //这里所谓的窗口是指n条数据写到内存后，直接转移到磁盘中，从而节约内存空间
        SXSSFWorkbook sw = new SXSSFWorkbook(100000);
        for (int i = 1; i <= 11; i++) {
            swList.add(sw.createSheet("s" + i));
        }
        //创建线程池，最大容纳4条线程
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(12));
        try {
            for (SXSSFSheet sxssfSheet : swList) {
                //创建线程对象，用于执行写Excelsheet页方法
                ExportHVThreadtest et = new ExportHVThreadtest(sxssfSheet, sxssfSheet.getSheetName().replace("s", "").toString());
                tpe.execute(() -> {
                    //执行写excel方法
                    et.run();
                    //每执行完一个线程方法，count数减一。
                    countDownLatch.countDown();
                    //一旦所有线程执行完毕，及执行write方法。所谓的write就是完成excel的数据插入操作。
                    OutputStream out = null ;
                    try {
                        out = new FileOutputStream("D:/test/test.xlsx");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("开始写入");
                    try {
                        sw.write(out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tpe.shutdown();
        }
        try {
           /* OutputStream out = new FileOutputStream("D:/test/test.xlsx");*/
            //只要countDown的数量大于0，那么主线程将一直处于阻塞状态
            countDownLatch.await();
        /*    //一旦所有线程执行完毕，及执行write方法。所谓的write就是完成excel的数据插入操作。
            System.out.println("开始写入");
            sw.write(out);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }


    public static void writeExcelOne(CrmWorkbenchService crmWorkbenchService, QueryCondition queryCondition, int current, String finalXlsxPath, Map<String, Object> params) {

        //每个sheet保存的数据量
        int num = 100000;
        ExcelWriter excelWriter = null;
        int corePoolSize = 11;
        int maximumPoolSize = 20;
        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<ReadExifInfoThreadOne> tasks = new ArrayList<ReadExifInfoThreadOne>();
        excelWriter = EasyExcel.write(finalXlsxPath, ExcelWorkbenchDto.class).build();
        //exifInfoCount 写入excel数据总量
        //pageCount 要写入sheet页数量。同分页
        //   int pageCount = exifInfoCount % num == 0 ? (exifInfoCount / num) : (exifInfoCount / num + 1);
        for (int i = 0; i < 11; i++) {
            ReadExifInfoThreadOne readExifInfoThread = new ReadExifInfoThreadOne(queryCondition, crmWorkbenchService, i, num, params);
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
        //  exector.shutdown();
        excelWriter.finish();






    }

    public static void writeExcelWebPoi(CrmWorkbenchService crmWorkbenchService, QueryCondition queryCondition, int current, String finalXlsxPath, Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) {

        long start=System.currentTimeMillis();
        //每个sheet保存的数据量
        int num = 100000;
        int corePoolSize = 11;
        int maximumPoolSize = 20;

        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<ReadExifInfoThreadWebPoi> tasks = new ArrayList<ReadExifInfoThreadWebPoi>();


        List<Future<List<ExcelWorkbenchDto>>> futures = null;
        try {

            for (int i = 0; i < corePoolSize; i++) {
                ReadExifInfoThreadWebPoi readExifInfoThread = new ReadExifInfoThreadWebPoi(queryCondition, crmWorkbenchService, i, num, params, response);
                tasks.add(readExifInfoThread);
            }
            futures = exector.invokeAll(tasks);



            System.out.println("执行等待"+(System.currentTimeMillis()-start));

            ArrayList<ExcelWorkbenchDto> list = new ArrayList<>();
            for (int i = 0; i < corePoolSize; i++) {
                list.addAll(futures.get(i).get());

            }

            MyExcelExportUtil.exportExcel(list,ExcelWorkbenchDto.class,"dw","ww",response,request);


            //写数据
         //   MyExcelExportUtil.exportExcel(list,ExcelWorkbenchDto.class,null,"新生入学信息",response);
            exector.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
