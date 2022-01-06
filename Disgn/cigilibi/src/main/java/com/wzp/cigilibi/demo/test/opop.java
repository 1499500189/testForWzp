package com.wzp.cigilibi.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

/**
 * @author
 * @date 2021 年 12 月 29 日
 */
public class opop {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }
    /***
     * 使用凯撒加密方式加密数据
     * @param orignal 原文
     * @param key 密钥
     * @return 加密后的字符
     */
    private static String encryptKaisa(String orignal, int key) {
        //将字符串转换为数组
        char[] chars = orignal.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //遍历数组
        for(char aChar : chars) {
            //获取字符的ASCII编码
            int asciiCode = aChar;
            //偏移数据
            asciiCode += key;
            //将偏移后的数据转为字符
            char result = (char)asciiCode;
            //拼接数据
            buffer.append(result);
        }
        return buffer.toString();
    }
}
