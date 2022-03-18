package com.wzp.test.niotest.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author
 * @date 2022 年 03 月 18 日
 */
//创建有两种方法
public class SocketChannelDemo {

    public static void main(String[] args) throws Exception {
        //第一种方法
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        //第二种方法
        SocketChannel socketChannel1 = SocketChannel.open();
        socketChannel1.connect(new InetSocketAddress("www.baidu.com",80));

        //设置阻塞或者非阻塞模式  ,false非阻塞
        socketChannel.configureBlocking(false);
        //读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");

    }
}
