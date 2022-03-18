package com.wzp.test.niotest.buffer;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author
 * @date 2022 年 03 月 18 日
 */
public class BufferDemo2 {
    @Test
    //缓冲区分片
    public  void b01(){
        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte)i);
        }
        //创建一个子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        //改变子缓冲区内容
        for (int i = 0; i <slice.capacity() ; i++) {
            byte b = slice.get(i);
            b*=10;
            slice.put(i,b);

        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }
    }
    //只读缓冲区
    @Test
    public void  b02 (){
        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte)i);
        }
        //创建一个只读的缓冲区
        ByteBuffer readOnly = buffer.asReadOnlyBuffer();


    }
    //直接缓冲区
    @Test
    public  void b03() throws IOException {
        String infile = "d:\\d\\wzp.txt";
        FileInputStream fil   =new FileInputStream(infile);
        FileChannel filChannel = fil.getChannel();


        String outfile ="d:\\d\\outwzp.txt";
        FileOutputStream fout = new FileOutputStream(outfile);
        FileChannel foutChannel = fout.getChannel();

        //创建直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true){
            buffer.clear();
            int r = filChannel.read(buffer);
            if (r==-1){
                break;
            }
            buffer.flip();
            foutChannel.write(buffer);
        }
    }
    static  private  final int start =0;
    static  private  final int size = 1024;
    //内存映射文件io
    @Test void b04() throws  Exception{
        RandomAccessFile accessFile = new RandomAccessFile("d:\\d\\wzp.txt", "rw");
        FileChannel fc = accessFile.getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);
        mbb.put(0,(byte) 97);
        mbb.put(1023,(byte) 122);
        accessFile.close();



    }
}
