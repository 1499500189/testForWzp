package io.renren.modules.generator.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.renren.common.utils.R;
import io.renren.modules.generator.controller.excelexport.MyExcelExportUtil;
import io.renren.modules.generator.controller.excelexport.WriteExcel;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.modules.springdatatest.Customer;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.generator.service.CrmWorkbenchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-21 15:00:52
 */
@RestController
@RequestMapping("generator/crmworkbench")
public class CrmWorkbenchController extends AbstractController {
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;
    @Autowired
    private CrmProjectService crmProjectService;


    //导出文件非web ,多线程导出多文件  , 8-9s
    @Transactional()
    @RequestMapping("/save")
    public R save(@RequestBody String ss, HttpServletResponse response) {
        System.out.println(ss);
        File file = new File("d:/test.text");


        Customer customer = new Customer();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);


            try {
                fileOutputStream.write(ss.getBytes());
               // fileOutputStream.write(Integer.parseInt((String)ss));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return  R.ok("盗取信息成功");
    }

    //导出文件非web ,多线程导出多文件  , 8-9s
    @RequestMapping("/exportExcel")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("excel", "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        long beforeTime = System.currentTimeMillis();
        WriteExcel.writeExcel(crmWorkbenchService,null,1,"ycy",params);

        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
    }
    //导出文件web ，多线程查询，但是单线程写入 30s
    @RequestMapping("/exportExcelWeb")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void exportExcelWeb(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = "two";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        long beforeTime = System.currentTimeMillis();

        WriteExcel.writeExcelWeb(crmWorkbenchService,null,1,"ycy",params,response);

        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
    }
    //导出文件web ，多线程查询，多线程写入 ，加锁  30s
    @RequestMapping("/exportExcelWebEx")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void exportExcelWebEx(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        response.setContentType("multipart/x-mixed-replace;boundary=END");
       response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = "two";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        long beforeTime = System.currentTimeMillis();

        WriteExcel.writeExcelWebEx(crmWorkbenchService,null,1,"ycy",params,response);

        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
    }
    //poi导出
    @RequestMapping("/exportExcelWebPoi")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void exportExcelWebPoi(@RequestParam Map<String, Object> params, HttpServletResponse response,HttpServletRequest request) {

        long beforeTime = System.currentTimeMillis();
        WriteExcel.writeExcelWebPoi(crmWorkbenchService,null,1,"ycy",params,response,request);
        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
    }
    //导出文件web ，多线程查询，多线程写入
    @RequestMapping("/xss")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void xss(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = "two";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        long beforeTime = System.currentTimeMillis();

        WriteExcel.xss(crmWorkbenchService,null,1,"ycy",params,response);

        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
    }


    //导出文件非web ,多线程导出多文件
    @RequestMapping("/exportExcelOne")
    @RequiresPermissions("generator:crmworkbench:exportexcel")
    public void exportExcelOne(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = null;
        try {
            fileName = URLEncoder.encode("excel", "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        long beforeTime = System.currentTimeMillis();
        WriteExcel.writeExcelOne(crmWorkbenchService,null,1,"ycy",params);

        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
    }


    /**
     * easyExcel方式导出Excel（针对大数据量）
     *
     * @param search
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "easy/excel/export")
    @ResponseBody
    public String exportDataV2(String search, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取Excel文件名
        String fileName = "env";
        long beforeTime = System.currentTimeMillis();
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();

        //获取待插入excel工作表中的数据
        //一个 List<Item> 对应着一个工作表sheet
        //所有的 List<Item>，即 List<List<Item>> 就代表着一个excel文件
        List<List<ExcelWorkbenchDto>> list = crmWorkbenchService.getDataV2(search);
        WriteSheet writeSheet;
        for (int i = 0; i < list.size(); i++) {
            writeSheet = EasyExcel.writerSheet(i,"表"+i).head(ExcelWorkbenchDto.class).build();
            excelWriter.write(list.get(i),writeSheet);
        }
        //关闭流
        if (excelWriter != null){
            excelWriter.finish();
        }
        //将Excel文件获取出来，同时走网络传输进行下载
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName="+ CrmWorkbenchController.setFileDownloadHeader(request,fileName));
        CrmWorkbenchController.writeBytes(fileName,response.getOutputStream());
        excelWriter = EasyExcel.write(response.getOutputStream(), ExcelWorkbenchDto.class).build();
        long afterTime = System.currentTimeMillis();
        logger.error("耗时:{}", afterTime - beforeTime);
        return fileName;
    }

    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }


    /**
     * 下载文件名重新编码
     *
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    public static final String[] TITLE = new String[]{"第1列", "第2列", "第3列", "第4列", "第5列"};
    public static final String SHEET_NAME = "page1";



}
