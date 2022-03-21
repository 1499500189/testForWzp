package com.wzp.test.niotest.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author
 * @date 2022 年 03 月 21 日
 */
public class CharsetDemo {
    public static void main(String[] args) throws Exception {
        //1。获取charset对象
        Charset charset = Charset.forName("UTF-8");
        //2.获取编码器对象
        CharsetEncoder charsetEncoder = charset.newEncoder();
        //3.创建buffer缓冲区
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("wzp sss");
        charBuffer.flip();
        //4.编码
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        //5.获取到解码器对象

        CharsetDecoder charsetDecoder =charset.newDecoder();

        //6。解码
        CharBuffer charBuffer1 = charsetDecoder.decode(byteBuffer);
        System.out.println("解码之后===》"+charBuffer1.toString());

        //7使用GBK解码
        byteBuffer.flip();
        CharBuffer charBuffer2 = charsetDecoder.decode(byteBuffer);
        System.out.println("解码之后GBK====》"+charBuffer2.toString());

        //获取charset锁支持的字符编码
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set= map.entrySet();
        for (Map.Entry<String,Charset> entry:set){
            System.out.println(entry.getKey()+"="+entry.getValue().toString());
        }

    }
}
