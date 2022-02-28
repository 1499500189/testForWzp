package io.renren.modules.generator.controller.excelexport;

import org.apache.poi.xssf.streaming.SXSSFSheet;

/**
 * @author
 * @date 2022 年 02 月 22 日
 */
public class ExportHVThreadtest extends Thread{
    private SXSSFSheet sheet; //sheet实体
    private String sheetCode; //sheet名
    public ExportHVThreadtest(SXSSFSheet sheet, String sheetCode) {
        this.sheet = sheet;
        this.sheetCode = sheetCode;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + sheetCode);

    }
}
