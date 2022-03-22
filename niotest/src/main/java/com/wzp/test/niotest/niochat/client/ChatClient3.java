package com.wzp.test.niotest.niochat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
//聊天室 ，客户端
public class ChatClient3 {
    //启动客户端方法
    public void startClient(String name) throws IOException {
        //连接服务器
        SocketChannel socketChannel =
                SocketChannel.open(new InetSocketAddress("localhost", 8000));
        //接受服务器端发送的响应
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        //创建线程
        new Thread(new ClientThread(selector)).start();

        //向服务器端发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.next();
            if (s!=null&&s.length()>0){
                socketChannel.write(Charset.forName("UTF-8").encode(name+" : "+s));
            }
        }

    }

    public static void main(String[] args) throws IOException {

        new ChatClient3().startClient("lisi --- ");
    }
}
