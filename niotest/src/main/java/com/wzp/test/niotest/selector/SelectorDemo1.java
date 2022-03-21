package com.wzp.test.niotest.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class SelectorDemo1 {
    public static void main(String[] args) {
        //创建selector
        try {
            Selector selector = Selector.open();
            //通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定连接
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            serverSocketChannel.bind(new InetSocketAddress(9999));
            SocketChannel sc = serverSocketChannel.accept();
            System.out.println(sc.getLocalAddress());
            //将通道注册到选择器上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //查询已经就绪通道草走哦
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历集合
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            System.out.println("是否");
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                System.out.println(key);
                //判断key就绪状态操作
                if (key.isAcceptable()) {
                    //是否是接受 a connection was accepted by a serverSocketChannel
                    System.out.println("接受");
                } else if (key.isConnectable()) {
                    System.out.println("连接");
                    //是否是连接 a connection was established with a remote server
                } else if (key.isReadable()) {

                } else if (key.isWritable()) {

                }

            }
            iterator.remove();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
