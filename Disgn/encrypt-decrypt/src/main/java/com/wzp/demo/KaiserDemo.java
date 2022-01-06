package com.wzp.demo;

/**
 * @author
 * @date 2022 年 01 月 05 日
 */
public class KaiserDemo {
    public static void main(String[] args) {
        //定义原文
        String input ="Hello World";
        //吧原文右移三位
        int key = 3;
        String s = encryptKaiser(input);
        System.out.println(s);
        String s1=  decryptKaiser(s,key);
        System.out.println(s1);
    }

    private static String decryptKaiser(String s, int key) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c: chars) {
             int b = c;
             b=b-key;
             //偏移数据
             char newB  =  (char)b;
             sb.append(newB);
        }
        return sb.toString();
    }

    private static String encryptKaiser(String input) {
        //抽取方法 ctrl+alt+m
        //把字符串变成字节数组
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c:chars) {
            int b = c ;
            b=b+3;
            char newb =(char)b;
            sb.append(newb);
        }
        //加密之后得
        System.out.println(sb);
        return sb.toString();
    }
}
