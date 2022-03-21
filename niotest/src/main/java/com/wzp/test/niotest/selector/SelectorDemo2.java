package com.wzp.test.niotest.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class SelectorDemo2 {
    //服务端代码
    @Test
    public void serverDemo() throws IOException {
        //1.获取服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.切换非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //3.创建buffer
        ByteBuffer ServerByteBuffer =ByteBuffer.allocate(1024);
        //4.绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(8080));
        //5.获取到selector选择器
        Selector selector = Selector.open();
        //6.通道注册到选择器上面，并且进行监听
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //7.选择器进行轮询， 哪个是就绪状态，进入后续得状态
        while (selector.select()>0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                boolean acceptable = next.isAcceptable();
                //判断是什么操作
                if (acceptable){
                    System.out.println("是");
                    //获取连接
                    SocketChannel accept = serverSocketChannel.accept();
                    //切换到非阻塞模式
                    accept.configureBlocking(false);
                    //注册
                    accept.register(selector,SelectionKey.OP_READ);
                }else if (next.isReadable()){
                    System.out.println("读");
                     SocketChannel channel =     (SocketChannel)next.channel();
                    ByteBuffer by = ByteBuffer.allocate(1024);
                    int length = 0 ;
                    while ((length=channel.read(by))>0){
                        by.flip();
                        System.out.println(new String(by.array(),0,length));
                        by.clear();
                    }
                }
            }
            iterator.remove();

        }

    }

    //客户端代码
    @Test
    public void clientDemo() throws IOException {
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
