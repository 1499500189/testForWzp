package io.renren.modules.employee.util;

/**
 * @author
 * @date 2021 年 11 月 05 日
 */

import java.util.UUID;

public class RanNum {

    /**
     * 生成随机数<br>
     * GUID： 即Globally Unique Identifier（全球唯一标识符） 也称作 UUID(Universally Unique
     * IDentifier) 。
     *
     * 所以GUID就是UUID。
     *
     * GUID是一个128位长的数字，一般用16进制表示。算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID。
     *
     * 从理论上讲，如果一台机器每秒产生10000000个GUID，则可以保证（概率意义上）3240年不重复。
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {
        //System.out.println(randomUUID());
        //System.out.println(randomUUID());
    }

}
