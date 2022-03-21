package com.wzp.test.niotest.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * @author
 * @date 2022 年 03 月 17 日
 */
public class FileChannelDemo1 {
    //FileChannel读取数据到buffer中
    public static void main(String[] args) throws IOException {
        //创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("d:\\d\\wzp.txt", "rw");
        FileChannel channel = aFile.getChannel();
        //创建Buffer
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //读取数据到buffer中
        int byteRead = channel.read(buf);
        while (byteRead != -1) {
            System.out.println("读取了数据 ：" + byteRead);
            buf.flip();
            buf.clear();
            byteRead = channel.read(buf);
        }
        aFile.close();
        System.out.println("数据处理完毕");


    }
}

class cc {

    public static void main(String[] args) throws IOException {
        //创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("d:\\d\\wzp.txt", "rw");
        FileChannel channel = aFile.getChannel();
        //创建Buffer
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //读取数据到buffer中
        int byteRead = channel.read(buf);


        System.out.println("读取了数据 ：" + byteRead);
        buf.flip();
        while (buf.hasRemaining()) {
            System.out.println((char) buf.get());
        }
        buf.clear();
        byteRead = channel.read(buf);

        aFile.close();
        System.out.println("数据处理完毕");
    }


}
class A{

    public static void main(String[] args)  throws  Exception{
        //1.获取到通道，绑定主机和端口号
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
        //2.切换到非阻塞
        socketChannel.configureBlocking(false);
        //3.创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4.写入buffer数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            byteBuffer.put((new Date()+"------=>"+scanner.next()).toString().getBytes());
            //5.切换模式
            byteBuffer.flip();
            //6，写入通道
            socketChannel.write(byteBuffer);
            //7.清空并且关闭
            byteBuffer.clear();

        }
    }
}
