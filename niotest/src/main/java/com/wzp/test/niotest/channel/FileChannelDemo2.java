package com.wzp.test.niotest.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author
 * @date 2022 年 03 月 17 日
 */
public class FileChannelDemo2 {
    public static void main(String[] args) throws IOException {
        //打开FileChannel
        RandomAccessFile accessFile = new RandomAccessFile("D:\\d\\wzp.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        //创建buffer对象
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String  newData = "data wzp ";
        buffer.clear();
        //写入数据
        buffer.put(newData.getBytes());

        buffer.flip();
        //FileChannel 完成最终实现
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }
        //关闭
        channel.close();

    }
}
