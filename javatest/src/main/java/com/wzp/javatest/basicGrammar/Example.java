package com.wzp.javatest.basicGrammar;

import java.util.Scanner;

/**
 * @author
 * @date 2022 年 04 月 12 日
 */
//这是一个通过位运算将英文字符串进行加密操作
public class Example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个英文字符串或者解密字符串");
        String password =scanner.nextLine(); //获取用户输入
        char[]  array =password.toCharArray();//获取字符数据
        for (int i = 0; i < array.length; i++) {//遍历字符数组
            array[i] =(char)(array[i]^20000); //对每个数组元素进行异或运算
        }
        System.out.println("加密或解密结果如下：");
        System.out.println(new String(array));  //输出密钥
    }
}
