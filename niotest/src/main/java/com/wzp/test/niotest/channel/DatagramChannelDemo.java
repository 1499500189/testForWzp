package com.wzp.test.niotest.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 * @author
 * @date 2022 年 03 月 18 日
 */
public class DatagramChannelDemo {
    //发送的实现
    @Test
    public void  sendDatagram()throws Exception {
        //打开 DatagramChannel
           DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress  sendAddress =new InetSocketAddress("127.0.0.1",9999);
        //发送
        while (true){
            ByteBuffer buffer =  ByteBuffer.wrap("发送  =>王志鹏".getBytes("UTF-8"));
            sendChannel.send(buffer,sendAddress);
            System.out.println("已经完成了发送");
            Thread.sleep(1000);
        }
    }
    //接收的实现
    @Test
    public void receiveDatagram(){
        //打开DatagramChannel
    try {
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress receiveAddress = new InetSocketAddress(9999);
        //绑定
        receiveChannel.bind(receiveAddress);
        //buffer
        ByteBuffer receiveBuffer =   ByteBuffer.allocate(1024);
        //接收
        while (true){
            receiveBuffer.clear();
            SocketAddress socketAddress = receiveChannel.receive(receiveBuffer);
            receiveBuffer.flip();
            System.out.println(socketAddress.toString());
            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
        }
    }catch (Exception e){
        e.printStackTrace();
    }

    }
    @Test
    public  void testConnect() {
        try {
            DatagramChannel connChannel = DatagramChannel.open();
            //绑定
             connChannel.bind(new InetSocketAddress(1111));
            //连接
            connChannel.connect(new InetSocketAddress("120.0.0.1",1111));
            connChannel.write(ByteBuffer.wrap("发送 =》 王志鹏".getBytes("UTF-8")));
            System.out.println("发--------");
            //buffer

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            while (true){
                readBuffer.clear();
                // connChannel.read(readBuffer);
                SocketAddress socketAddress = connChannel.receive(readBuffer);
                readBuffer.flip();
                System.out.println(Charset.forName("UTF-8").decode(readBuffer));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
//    public static void main(String[] args) throws Exception {
//        DatagramChannel connChannel = DatagramChannel.open();
//        //绑定
//        connChannel.bind(new InetSocketAddress(1111));
//        //连接
//        connChannel.connect(new InetSocketAddress("120.0.0.1",1111));
//        connChannel.write(ByteBuffer.wrap("发送 =》 王志鹏".getBytes("UTF-8")));
//        //buffer
//        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
//        while (true){
//            readBuffer.clear();
//            connChannel.read(readBuffer);
//            readBuffer.flip();
//            System.out.println(Charset.forName("UTF-8").decode(readBuffer));
//        }
//
//    }
}
