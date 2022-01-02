package com.wzp.user.benmon.builder;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class Client {
    public static void main(String[] args) {

        AbstractHouse commonHouse = new CommonHouse();
        commonHouse.build();
    }
}
