package com.wzp.javatest.basicGrammar;

/**
 * @author
 * @date 2022 年 04 月 12 日
 */
public class TypeConversion {
    public static void main(String[] args) {
        byte b =127;
        char c ='w';
        short s =23561;
        int  i= 3333;
        long l = 4000000000L;
        float  f =3.14159F;
        double d =54.523;
        //低类型向该类型自动转换
        System.out.println("累加byte等于："+b);
        System.out.println("累加char等于：" +(b+c));
        System.out.println("累加short等于："+(b+c+s));


        System.out.println("long强制类型转换为 int ："+(int)l);

    }
}
