package com.wzp.demo;

/**
 * @author
 * @date 2022 年 01 月 05 日
 */
public class AsciiDemo {
    public static void main(String[] args) {
    //    char a ='A';
   //     int b = a ;
        //打印b，在asc码中是多少
    //    System.out.println(b);
        String a= "Aaz";
        char[] chars = a.toCharArray();
        for (char c:
             chars) {
            int asciicode =c;
            System.out.println(asciicode);
        }
    }
}
