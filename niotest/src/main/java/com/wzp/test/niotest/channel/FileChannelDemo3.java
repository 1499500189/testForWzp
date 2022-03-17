package com.wzp.test.niotest.channel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author
 * @date 2022 年 03 月 17 日
 */
public class FileChannelDemo3 {
    public static void main(String[] args) throws  Exception {
        RandomAccessFile aFile = new RandomAccessFile("d:\\d\\wzp.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();
        RandomAccessFile bFile = new RandomAccessFile("d:\\d\\wzp3.txt", "rw");
        FileChannel toChannel = bFile.getChannel();
        long position = 15;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel,position,count);
        aFile.close();
        bFile.close();
        System.out.println("传输结束");

    }
}
