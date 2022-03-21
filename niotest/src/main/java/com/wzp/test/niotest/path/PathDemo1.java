package com.wzp.test.niotest.path;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class PathDemo1 {
    public static void main(String[] args) {
        //创建path实例
        Path path = Paths.get("d:\\d\\wzp.txt");
        //创建相对路径
        Path path1 = Paths.get("d:\\d","wzp.txt");
        //使path名字标准化
        Path normalize = path.normalize();


    }
}
