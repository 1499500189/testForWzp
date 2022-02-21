package io.renren.modules.generator.util;

import io.renren.modules.generator.task.PageTask;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExcelUtil {
    public static final int PER_SHEET_LIMIT = 500000;

    public static SXSSFWorkbook getSXSSFWorkbookByPageThread(String[] title, String[][] values) {

        SXSSFWorkbook wb = new SXSSFWorkbook();

        int pageNum = values.length / PER_SHEET_LIMIT;
        int lastCount = values.length % PER_SHEET_LIMIT;

        if (values.length > PER_SHEET_LIMIT) {

            CellStyle style = wb.createCellStyle();
            int sheet = lastCount == 0 ? pageNum : pageNum + 1;
            CountDownLatch downLatch = new CountDownLatch(sheet);
            Executor executor = Executors.newFixedThreadPool(sheet);

            for (int c = 0; c <= pageNum; c++) {
                int rowNum = PER_SHEET_LIMIT;
                if (c == pageNum) {
                    if (lastCount == 0) {
                        continue;
                    }
                    rowNum = lastCount;
                }
                Sheet sheet2 = wb.createSheet("page" + c);
                executor.execute(new PageTask(downLatch, sheet2, title, style, rowNum, values));
            }
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }
}
