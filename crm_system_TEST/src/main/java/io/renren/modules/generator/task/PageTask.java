package io.renren.modules.generator.task;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.concurrent.CountDownLatch;

public class PageTask implements Runnable {
    private CountDownLatch countDownLatch;

    private Sheet sheet;
    private String[] title;
    private CellStyle style;
    private int b;
    private String[][] values;

    public PageTask(CountDownLatch countDownLatch, Sheet sheet, String[] title, CellStyle style, int b, String[][] values) {
        this.countDownLatch = countDownLatch;
        this.sheet = sheet;
        this.title = title;
        this.style = style;
        this.b = b;
        this.values = values;
    }

    @Override
    public void run() {

        try {
            Row row = sheet.createRow(0);

            Cell cell = null;

            for (int i = 0; i < title.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }

            for (int i = 0; i < b; i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < values[i].length; j++) {
                    row.createCell(j).setCellValue(values[i][j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
