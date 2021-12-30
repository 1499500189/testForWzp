package com.wzp.cigilibi.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class test {
    public static void objectToTrim(Object obj) throws IllegalAccessException {

        Map map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();

        for (Field field:declaredFields){

            String type = field.getType().getCanonicalName();
            System.out.println(type);

            if (("java.lang.String").equals(type)){

                field.setAccessible(true);

                String str = (String)field.get(obj);

                if (str != null) {
                   //  String trim = object.toString().replace(" ","");
                    String trim = str.trim();
                    map.put(field.getName(),trim);
                }

            }

        }
        for (Field field:declaredFields){

            if (map.get(field.getName())!=null){

                String s = (String) map.get(field.getName());

                field.setAccessible(true);

                field.set(obj,s);

            }

        }

    }

    public static void main(String[] args) throws IllegalAccessException {

        IdCode idCode = new IdCode();

        idCode.setDeleteFlag(1);

        idCode.setCreateDate(LocalDateTime.now());

        idCode.setDeptId(" ");

        idCode.setEducationUrl(" abvc");

        idCode.setIdCode(" b c ddd");

        System.out.println(idCode.toString());

        System.out.println("===================去掉了空格=====================");

        objectToTrim(idCode);
        System.out.println(idCode.getCreateDate());
        System.out.println(idCode.getDeleteFlag());
        System.out.println("id"+idCode.getDeptId());
        System.out.println(idCode.getEducationUrl());
        System.out.println(idCode.getIdCode());
        System.out.println(IdCode.getSs());
    }
}
class IdCode{

    private  Integer deleteFlag;
    private LocalDateTime createDate;
    private  String deptId;
    private  String educationUrl;
    private  String idCode;
    private static  String ss =" wd a " ;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getEducationUrl() {
        return educationUrl;
    }

    public void setEducationUrl(String educationUrl) {
        this.educationUrl = educationUrl;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public static String getSs() {
        return ss;
    }

    public static void setSs(String ss) {
        IdCode.ss = ss;
    }
}
