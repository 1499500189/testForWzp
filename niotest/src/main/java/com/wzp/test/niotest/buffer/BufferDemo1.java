package com.wzp.test.niotest.buffer;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author
 * @date 2022 年 03 月 18 日
 */
public class BufferDemo1 {
    @Test
    public void buffer01(){
        //FileChannel
        try {
            RandomAccessFile accessFile = new RandomAccessFile("d:\\d\\wzp.txt","rw");
            FileChannel channel = accessFile.getChannel();
            //创建buffer，大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead  = channel.read(buffer);
             while (bytesRead!=-1){
                 //read模式
                 buffer.flip();
                 while (buffer.hasRemaining()) {
                     System.out.println((char) buffer.get());
                 }
                 buffer.clear();
                 bytesRead  =channel.read(buffer);
             }
             accessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public  void buffer02(){
        //创建一个buffer
        IntBuffer  buffer = IntBuffer.allocate(1024);

        //向buffer中放入数据
        for (int i = 0; i <buffer.capacity(); i++) {
            int j  =  2*(i+1);
            buffer.put(j);
        }
        //重置一个缓冲区
        buffer.flip();
        //获取
        while(buffer.hasRemaining()){
            int value = buffer.get();
            System.out.println(value+"  =====");

        }
    }
}
