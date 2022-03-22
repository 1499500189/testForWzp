package com.wzp.test.niotest.niochat.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author
 * @date 2022 年 03 月 22 日
 */
public class ClientThread implements  Runnable{

    private Selector selector;
    public    ClientThread(Selector selector){
        this.selector=selector;


    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (; ; ) {
            //获取channel数量
            int readChannels = 0;
            try {
                readChannels = selector.select();
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
                    if (selectionKey.isReadable()) {
                        //do something
                     //   System.out.println("可读状态====》");
                        readOperator(selector,selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //5.根据就绪的状态，调用对应方法实现具体业务操作

        //5.1如果accept状态
        //5.2如果可读状态
    }
    private void readOperator(Selector selector, SelectionKey selectionKey) throws IOException {
      //  System.out.println("进入可读");
        //从selectionKey获取已经就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // SelectableChannel channel = selectionKey.channel();
        //2创建buffer
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        //3.循环读取客户端消息
        int readLength = socketChannel.read(byteBuffer);
        String message ="";
        if (readLength>0){
            //切换读模式
            byteBuffer.flip();
            //读取内容
            message+= Charset.forName("UTF-8").decode(byteBuffer);
        }
        //4.把channel注册到客户选择器上，监听可读状态
        socketChannel.register(selector,SelectionKey.OP_READ);
        //5.把客户端发送消息，广播到其他的客户端中
        if (message.length()>0){
         //   System.out.println("客户端获取广播的数据");
            System.out.println(message);
        }else {
            System.out.println("feaf");
        }

    }
}
