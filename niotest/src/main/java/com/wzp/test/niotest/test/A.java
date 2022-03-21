package com.wzp.test.niotest.test;

import java.io.IOException;
import java.nio.channels.Pipe;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class A {
    public static void main(String[] args) {
        try {
            Pipe open = Pipe.open();
            Pipe.SinkChannel sink = open.sink();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class    B{

    }
}
