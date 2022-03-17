package io.renren.modules.dynamic_object;

import com.qiniu.util.Md5;
import io.renren.modules.generator.dao.po.GraphicalStatisticsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;
import sun.security.provider.MD5;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @date 2022 年 03 月 17 日
 */
public class ClassUtil {
    private String filePath = "/config/"; //配置文件路径

    public String getFilePath() {
        return filePath;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

//    public Object dynamicClass(Object object) throws Exception {
//        HashMap returnMap = new HashMap();
//        HashMap typeMap = new HashMap();
//        //读取配置文件
//        Properties prop = new Properties();
//        String sourcepackage = object.getClass().getName();
//        String classname = sourcepackage.substring(sourcepackage.lastIndexOf(".") + 1);
//        InputStream in = ClassUtil.class.getResourceAsStream(filePath + classname + ".properties");
//        prop.load(in);
//
//        Set<String> keylist = prop.stringPropertyNames();
//
//        Class type = object.getClass();
//        BeanInfo beanInfo = Introspector.getBeanInfo(type);
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for(int i = 0; i < propertyDescriptors.length; i++) {
//            PropertyDescriptor descriptor = propertyDescriptors[i];
//            String propertyName = descriptor.getName();
//            if(!propertyName.equals("class")) {
//                Method readMethod = descriptor.getReadMethod();
//                Object result = readMethod.invoke(object, new Object[0]);
//                if(result != null) {
//                    returnMap.put(propertyName, result);
//                } else {
//                    returnMap.put(propertyName, "");
//                }
//                typeMap.put(propertyName, descriptor.getPropertyType());
//            }
//        }
//        //加载配置文件中的属性
//        Iterator<String> iterator = keylist.iterator();
//        while(iterator.hasNext()) {
//            String key = iterator.next();
//            returnMap.put(key, prop.getProperty(key));
//            typeMap.put(key, Class.forName("java.lang.String"));
//        }
//        //map转换成实体对象
//        DynamicBean bean = new DynamicBean(typeMap);
//        //赋值
//        Set keys = typeMap.keySet();
//        for(Iterator it = keys.iterator(); it.hasNext(); ) {
//            String key = (String) it.next();
//            bean.setValue(key, returnMap.get(key));
//        }
//        Object obj = bean.getObject();
//        return obj;
//    }

    //传递object 目前不知道是什么 ， keylist是动态生成的属性列
    public Object dynamicClass(Object object ,Set<String> keylist  ) throws Exception {
        HashMap returnMap = new HashMap();
        HashMap typeMap = new HashMap();
        //读取配置文件
//        Properties prop = new Properties();
//        String sourcepackage = object.getClass().getName();
//        String classname = sourcepackage.substring(sourcepackage.lastIndexOf(".") + 1);
//        InputStream in = ClassUtil.class.getResourceAsStream(filePath + classname + ".properties");
//        prop.load(in);

      //  Set<String> keylist = prop.stringPropertyNames();

        Class type = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for(int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if(!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(object, new Object[0]);
                if(result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
                typeMap.put(propertyName, descriptor.getPropertyType());
            }
        }
        Properties prop = new Properties();
        //加载配置文件中的属性
        Iterator<String> iterator = keylist.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            String s = Md5.md5(key.getBytes());

            returnMap.put(s, prop.getProperty(s));
            typeMap.put(s, Class.forName("java.lang.String"));
        }
        //map转换成实体对象
        DynamicBean bean = new DynamicBean(typeMap);
        //赋值
        Set keys = typeMap.keySet();
        for(Iterator it = keys.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            bean.setValue(key, returnMap.get(key));
        }

        DynamicBean bea1 = new DynamicBean(typeMap);

        Object obj = bean.getObject();
        System.out.println("对象的"+obj);
        System.out.println("对象的"+bea1.getObject());
        return obj;
    }
  //返回拼接的用于生成动态类对象的方法
    public HashMap dynamicObject(Object object ,Set<String> keylist  ) throws Exception {
        HashMap returnMap = new HashMap();
        HashMap typeMap = new HashMap();
        //读取配置文件
//        Properties prop = new Properties();
//        String sourcepackage = object.getClass().getName();
//        String classname = sourcepackage.substring(sourcepackage.lastIndexOf(".") + 1);
//        InputStream in = ClassUtil.class.getResourceAsStream(filePath + classname + ".properties");
//        prop.load(in);

        //  Set<String> keylist = prop.stringPropertyNames();

        Class type = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for(int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if(!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(object, new Object[0]);
                if(result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
                typeMap.put(propertyName, descriptor.getPropertyType());
            }
        }
        Properties prop = new Properties();
        //加载配置文件中的属性
        Iterator<String> iterator = keylist.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            String s = Md5.md5(key.getBytes());

            returnMap.put(s, prop.getProperty(s));
            typeMap.put(s, Class.forName("java.lang.Integer"));
        }
        //map转换成实体对象
       // DynamicBean bean = new DynamicBean(typeMap);
        //赋值
//        Set keys = typeMap.keySet();
//        for(Iterator it = keys.iterator(); it.hasNext(); ) {
//            String key = (String) it.next();
//            bean.setValue(key, returnMap.get(key));
//        }

        //DynamicBean bea1 = new DynamicBean(typeMap);

        return typeMap;
    }
    public static void main(String[] args) throws Exception {
//        new ClassUtil().dynamicClass(new GraphicalChartVo(),);


    }
}
