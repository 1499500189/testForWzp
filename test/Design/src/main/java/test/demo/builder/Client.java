package main.java.test.demo.builder;

import test.demo.builder.AbstractHouse;
import test.demo.builder.CommonHouse;

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
