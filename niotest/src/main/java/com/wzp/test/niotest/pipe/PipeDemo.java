package com.wzp.test.niotest.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        //1获取管道
        Pipe pipe = Pipe.open();
        //2获取sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        //3创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("wzp".getBytes());
        byteBuffer.flip();
        //4.写入数据
        sinkChannel.write(byteBuffer);
        //5.获取source 通道
        Pipe.SourceChannel sourceChannel = pipe.source();
        //6创建缓冲区，读取数据
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
      //  byteBuffer2.flip();
        int length  =sourceChannel.read(byteBuffer2);
        System.out.println(new String(byteBuffer2.array(),0,length));
        //7关闭通道
         sinkChannel.close();
         sourceChannel.close();
        System.out.println("结束");

    }
}
