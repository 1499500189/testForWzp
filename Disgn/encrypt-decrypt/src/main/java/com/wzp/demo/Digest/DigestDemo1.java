package com.wzp.demo.Digest;

import com.alibaba.nacos.common.utils.MD5Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author
 * @date 2022 年 01 月 06 日
 */
//消息摘要算法，未来防止篡改
public class DigestDemo1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
       int i =15;
        System.out.println(i>>2);
        System.out.println(i>>>2);
        int  j  =-10 ;
        System.out.println(j>>2);
        System.out.println(j>>>3);

        //返回一个摘要对象
    }
}
