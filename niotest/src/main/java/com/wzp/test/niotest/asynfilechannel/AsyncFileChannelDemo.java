package com.wzp.test.niotest.asynfilechannel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
//异步的
public class AsyncFileChannelDemo {
    @Test //
    public void writeAsyncFileComplate() throws Exception{
        //1.创建一个AsyncFileChannel
        Path path = Paths.get("d:\\d\\wzp.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE,StandardOpenOption.SPARSE);
        //2创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("wzp dd".getBytes());
        buffer.flip();
        //3调用channel的read方法得到Future
      fileChannel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });


    }
    @Test//写数据
    public  void writeAsyncFileFuture() throws Exception{
        //1.创建一个AsyncFileChannel
        Path path = Paths.get("d:\\d\\wzp.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        //2创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("wzp dd".getBytes());
        buffer.flip();
        //3调用channel的read方法得到Future
        Future<Integer> future = fileChannel.write(buffer, 0);
        while (!future.isDone()){
            System.out.println("write over");
            buffer.clear();
        }

    }


    @Test
    public void readAsyncFileChannelFuture() throws IOException {
        //1.创建一个AsyncFileChannel
        Path path = Paths.get("d:\\d\\wzp.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        //2创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3调用channel的read方法得到Future
        Future<Integer> future = fileChannel.read(buffer, 0);
        //4判断是否完成 isDone ，如果返回true
        while (!future.isDone()) {

        }
        //5读取数据到buffer里面
        buffer.flip();
       /* while (buffer.remaining()>0){
            byte b = buffer.get();
            System.out.println(b);
        }*/
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();

    }

    @Test//第二种方式
    public void readAsyncFileChannelCompletion() throws IOException {
        //1.创建一个AsyncFileChannel
        Path path = Paths.get("d:\\d\\wzp.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path);
        //2创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3调用channel的read方法得到Future
        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result:===>" + result);
                //5读取数据到buffer里面
                attachment.flip();
       /* while (buffer.remaining()>0){
            byte b = buffer.get();
            System.out.println(b);
        }*/
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                buffer.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });


    }


}
