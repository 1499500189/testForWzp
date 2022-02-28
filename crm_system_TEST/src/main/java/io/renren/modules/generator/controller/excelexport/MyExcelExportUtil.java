package io.renren.modules.generator.controller.excelexport;


import cn.afterturn.easypoi.csv.CsvExportUtil;
import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.poi.excel.BigExcelWriter;

import cn.hutool.poi.excel.ExcelUtil;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author
 * @date 2022 年 02 月 23 日
 */
public class MyExcelExportUtil {
    /**
     * Excel文件导出,导出的文件名默认为:headTitle+当前系统时间
     * @param listData 要导出的list数据
     * @param pojoClass 定义excel属性信息
     * @param headTitle Excel文件头信息
     * @param sheetName Excel文件sheet名称
     * @param response
     */
    public static void exportExcel(List listData, Class<?> pojoClass, String headTitle, String sheetName, HttpServletResponse response, HttpServletRequest request) {
        OutputStream out = null;

        CsvExportParams csvExportParams = new CsvExportParams();
        try {
            out=response.getOutputStream();
            String fileName = "10011.csv";


            // 产生工作簿对象
           // SXSSFWorkbook  workbook = new SXSSFWorkbook ();
          //  Workbook workbook = ExcelExportUtil.exportExcel(params, pojoClass, listData);
            String[] titles = new String[]{"日期", "客户手机号","项目名称","项目类别","降后金额","套餐金额","直降金额","积分总数","业绩数","是否有效","所属人","工号"};
            String[] propertys = new String[]{"processingDate","telephone","projectName","categoryName","reducedAmount","packageAmount","downAmount","totalPoints","numberAchievements","isTrue","username","jobNumber"};
            // 读取字符编码
            String csvEncoding = "UTF-8";
            // 设置响应
            response.setCharacterEncoding(csvEncoding);
            response.setContentType("text/csv; charset=" + csvEncoding);
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=30");

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            exportCsv(titles,propertys,listData,response);
//            exportCsvString(titles,propertys,listData,response,request);
           // CSVUtils.exportCsv("d:\\test.csv",listData.toString(),response,request);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            }catch (IOException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }
    /**
     *
     *     导出生成csv格式的文件
     * @author   ccg
     * @param   titles csv格式头文
     * @param   propertys 需要导出的数据实体的属性，注意与title一一对应
     * @param   list 需要导出的对象集合
     * @return
     * @throws   IOException
     * Created   2017年1月5日 上午10:51:44
     * @throws   IllegalAccessException
     * @throws   IllegalArgumentException
     */
    public static <T> String exportCsv(String[] titles, String[] propertys, List<T> list,HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException{

        File file = new File("d:\\test.csv");
        //构建输出流，同时指定编码
        OutputStreamWriter ow = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        //csv文件是逗号分隔，除第一个外，每次写入一个单元格数据后需要输入逗号
        for(String title : titles){
            ow.write(title);
            ow.write(",");
        }
        //写完文件头后换行
        ow.write("\r\n");
//        写内容
        for(Object obj : list){
            //利用反射获取所有字段
            Field[] fields = obj.getClass().getDeclaredFields();
            /*for(String property : propertys){*/
                for(Field field : fields){

                    //设置字段可见性
                    field.setAccessible(true);
                   /* if(property.equals(field.getName())){*/
                        if (field.get(obj)!=null){
                            ow.write(field.get(obj).toString());
                        }else {
                            ow.write(" ");
                        }
                        ow.write(",");
                       /* continue;*/
                   /* }*/
                }
          /*  }*/
            //写完一行换行
            ow.write("\r\n");
        }
        ow.flush();
        ow.close();
        return "0";
    }
    public static <T> String exportCsvUtils(String content,HttpServletResponse response,HttpServletRequest request){
        String fileName ="xxx.csv";
        // 读取字符编码
        String csvEncoding = "UTF-8";
        // 设置响应
        response.setCharacterEncoding(csvEncoding);
        response.setContentType("text/csv; charset=" + csvEncoding);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        OutputStream os = null;
        try {
            final String userAgent = request.getHeader("USER-AGENT");
            if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
                fileName = URLEncoder.encode(fileName,"UTF8");
            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            }else{
                fileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            // 写出响应
            os = response.getOutputStream();
            os.write(content.getBytes("GBK"));

        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }finally {
            try {
                os.flush();
                os.close();
            }catch (IOException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        return "3";
    }
    public static <T> String exportCsvString(String[] titles, String[] propertys, List<T> list,HttpServletResponse response,HttpServletRequest request) throws IOException, IllegalArgumentException, IllegalAccessException{


        //构建输出流，同时指定编码
        OutputStreamWriter ow = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        //csv文件是逗号分隔，除第一个外，每次写入一个单元格数据后需要输入逗号
        for(String title : titles){
            stringBuilder.append(title);
            stringBuilder.append(",");
        }
        //写完文件头后换行
        stringBuilder.append("\r\n");
//        写内容
        for(Object obj : list){
            //利用反射获取所有字段
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field field : fields){
                //设置字段可见性
                field.setAccessible(true);
                if (field.get(obj)!=null){
                    stringBuilder.append(field.get(obj).toString());
                }else {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(",");
            }
            //写完一行换行
            stringBuilder.append("\r\n");
        }
        exportCsvUtils(stringBuilder.toString(),response,request);
       /* ow.write(stringBuilder.toString());
        ow.flush();
        ow.close();*/
        return "0";
    }
}
