package com.wzp.test.niotest.niochat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class ChatServer {
    //服务器端启动的方法
    public void startServer() throws IOException {
        //1.创建Selector选择器
        Selector selector = Selector.open();
        //2.创建ServerSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.为channel通道绑定监听通道
        serverSocketChannel.bind(new InetSocketAddress(8000));
        //设置非阻塞状态
        serverSocketChannel.configureBlocking(false);
        //4. 先注册到seletor选择器上  循环，等待有新连接接入
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器正常启动成功，  进入接受");
        for (; ; ) {
            //获取channel数量
            int readChannels = selector.select();
            if (readChannels == 0) {
                continue;
            }
            //获取到可用的channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //移除set集合当前selectionkey

                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    //do something
                    System.out.println("接受状态===");
                    acceptOperatpr(serverSocketChannel, selector);
                }
                if (selectionKey.isReadable()) {
                    //do something
                    System.out.println("可读状态====》");
                    readOperator(selector,selectionKey);
                }


            }
        }
        //5.根据就绪的状态，调用对应方法实现具体业务操作

        //5.1如果accept状态
        //5.2如果可读状态
    }

    private void readOperator(Selector selector, SelectionKey selectionKey) throws IOException {
        //从selectionKey获取已经就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        SelectableChannel channel = selectionKey.channel();
        //2创建buffer
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        //3.循环读取客户端消息
        int readLength = socketChannel.read(byteBuffer);
        String message ="";
        while (readLength>0){
            //切换读模式
            byteBuffer.flip();
            //读取内容
            message  +=   Charset.forName("UTF-8").decode(byteBuffer);

        }
        //4.把channel注册到客户选择器上，监听可读状态
        socketChannel.register(selector,SelectionKey.OP_READ);
        //5.把客户端发送消息，广播到其他的客户端中
        if (message.length()>0){
            System.out.println("进行广播操作");
        }

    }

    //处理接入状态操作
    private void acceptOperatpr(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        //1接入状态，创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        Socket socket = socketChannel.socket();
        InetAddress inetAddress = socket.getInetAddress();
        System.out.println(inetAddress.getAddress()+"===="+inetAddress.getHostAddress()+"==="+inetAddress.toString());
        //2.把socketChannel设置非阻塞模式
        socketChannel.configureBlocking(false);
        //3.把channel注册到selector选择器上， 监听可读状态
        socketChannel.register(selector,SelectionKey.OP_READ);
        //4.客户端恢复信息
        socketChannel.write(Charset.forName("UTF-8").encode("欢迎进入聊天室，请注意隐私的安全"));




    }

    public static void main(String[] args) {

    }
}
