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
public class FileChannelDemo1 {
    //FileChannel读取数据到buffer中
    public static void main(String[] args) throws IOException {
        //创建FileChannel
        RandomAccessFile aFile =new RandomAccessFile("d:\\d\\wzp.txt","rw");
        FileChannel channel = aFile.getChannel();
        //创建Buffer
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //读取数据到buffer中
        int byteRead = channel.read(buf);
        while (byteRead!=-1){
            System.out.println("读取了数据 ："+byteRead);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            buf.clear();
            byteRead = channel.read(buf);
        }
        aFile.close();
        System.out.println("数据处理完毕");


    }
}
