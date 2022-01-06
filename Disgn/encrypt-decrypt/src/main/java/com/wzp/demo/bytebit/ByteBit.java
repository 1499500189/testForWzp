package com.wzp.demo.bytebit;

/**
 * @author
 * @date 2022 年 01 月 05 日
 */
public class ByteBit {
    public static void main(String[] args) {
        String a ="a";
        byte[] bytes = a.getBytes();
        for (byte v: bytes ) {
            System.out.println(v);
            //byte 字节, 对应得bit是多少
            String s = Integer.toBinaryString(v);
            System.out.println(s);
        }
    }
}
