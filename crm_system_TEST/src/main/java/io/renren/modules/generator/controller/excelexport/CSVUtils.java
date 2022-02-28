package io.renren.modules.generator.controller.excelexport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVUtils {
    /** CSV文件列分隔符 */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /** CSV文件列分隔符 */
    private static final String CSV_RN = "\r\n";
    private final static Logger logger = LoggerFactory.getLogger(CSVUtils.class);

    /**
     * 数据初始化
     *
     * @param data                  数据
     * @param displayColNames       表头
     * @param matchColNames         data中的key
     * @return
     */
    public static String formatCsvData(List<Map<String, Object>> data, String displayColNames, String matchColNames){
        StringBuffer buf = new StringBuffer();
        String[] displayColNamesArr1 = null;
        String[] matchColNamesMapArr1 = null;
        //筛选key和name
        displayColNamesArr1 = displayColNames.split(",");
        List<String> list1 = new ArrayList<>();
        for (int i=0; i<displayColNamesArr1.length; i++){
            list1.add(displayColNamesArr1[i]);
        }
        String[] displayColNamesArr = new String[list1.size()];
        displayColNamesArr = list1.toArray(displayColNamesArr);

        matchColNamesMapArr1 = matchColNames.split(",");
        List<String> list2 = new ArrayList<>();
        for (int i=0; i<matchColNamesMapArr1.length; i++){
            list2.add(matchColNamesMapArr1[i]);
        }
        String[] matchColNamesMapArr = new String[list2.size()];
        matchColNamesMapArr = list2.toArray(matchColNamesMapArr);

        // 输出列头
        for (int i = 0; i < displayColNamesArr.length; i++) {
            buf.append(displayColNamesArr[i]).append(CSV_COLUMN_SEPARATOR);
        }
        buf.append(CSV_RN);

        if (null != data) {
            // 输出数据
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < matchColNamesMapArr.length; j++) {
                    //处理list<Map>中 value=null的数据
                    Object object = data.get(i).get(matchColNamesMapArr[j]);
                    if(object==null){
                        object = data.get(i).get(matchColNamesMapArr[j].substring(1));
                    }
                    if(object==null){
                        buf.append(CSV_COLUMN_SEPARATOR);
                    }else{
                        if(matchColNamesMapArr[j].startsWith("-")){
                            buf.append("\t" +object.toString()).append(CSV_COLUMN_SEPARATOR);
                        }else{
                            buf.append(object).append(CSV_COLUMN_SEPARATOR);
                        }
                    }
                }
                buf.append(CSV_RN);
            }
        }
        logger.info("csv file Initialize successfully");
        return buf.toString();
    }

    /**
     * 导出
     *
     * @param fileName  文件名
     * @param content   内容
     * @param request
     * @param response
     * @throws IOException
     */
    public static void exportCsv(String fileName, String content, HttpServletResponse response, HttpServletRequest request) {
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
            logger.info("csv file download completed");
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
    }


}
