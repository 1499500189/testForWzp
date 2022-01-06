package com.wzp.cigilibi.demo.test;

import java.util.Scanner;

/**
 * @author
 * @date 2022 年 01 月 04 日
 */
public class ono {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str;

        while (true) {

            str = sc.next();

            str = str.replace("吗", "");

            str = str.replace("?", "!");

            str = str.replace("？", "！");

            System.out.println(str);

        }
    }
}
