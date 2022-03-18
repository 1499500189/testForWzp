package com.wzp.test.niotest.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author
 * @date 2022 年 03 月 18 日
 */
//监听
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        //端口号
        int port =8888;
        //buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello  wzp".getBytes());

        //serverSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定
        ssc.socket().bind(new InetSocketAddress(port));
        //设置非阻塞模式
        ssc.configureBlocking(false);

        //监听有新连接传入
        while (true){
            SocketChannel sc = ssc.accept();

            if (sc ==null){
                System.out.println("null");
                System.out.println("没有新的连接传入");
                Thread.sleep(2000);
            }else {
                System.out.println("输出"+sc.socket().getRemoteSocketAddress());
                buffer.rewind();//指针指向0
                sc.write(buffer);//写入
                sc.close();//关闭
            }



        }
    }
}
