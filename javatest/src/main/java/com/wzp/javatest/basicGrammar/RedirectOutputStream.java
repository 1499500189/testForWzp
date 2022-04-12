package com.wzp.javatest.basicGrammar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author
 * @date 2022 年 04 月 12 日
 */
public class RedirectOutputStream {
    public static void main(String[] args) {
        try {
            PrintStream out = System.out;
            PrintStream ps =new PrintStream("./log.txt");
            System.out.println(new File(".").getAbsolutePath());
            ps.print("csac");
            PrintStream printStream = new PrintStream("d:/mingling.txt");
            printStream.print("测试争取");
            System.out.println(ps+"ps");
            System.setOut(ps);
            int age =18;
            System.out.println("年龄变量成功定义，初始值为18");
            String sex ="女";
            System.out.println("性别变量成功定义，初始值为女");
            String info ="这是个"+sex +"孩子，应该有"+age+"岁了";
            System.out.println("info 变量值"+info);
            System.setOut(out);
            System.out.println("程序运行完毕，请查看日志文件");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
