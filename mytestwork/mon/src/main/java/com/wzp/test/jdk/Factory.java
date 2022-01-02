package com.wzp.test.jdk;

import java.util.Calendar;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public class Factory {
    public static void main(String[] args) {
        Calendar  cal =Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
    }
}
