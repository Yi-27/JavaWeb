package com.example.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author Yi-27
 * @create 2020-10-31 7:47
 */
public class Base64Test {

    public static void main(String[] args) throws IOException {

        String content = "这是需要用Base64编码的内容";
        // 创建一个Base64编码器
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 执行编码操作
        String encodeString = base64Encoder.encode(content.getBytes("UTF-8"));
        System.out.println(encodeString);

        // 创建Base64解码器
        BASE64Decoder base64Decoder = new BASE64Decoder();
        // 执行解码操作
        byte[] bytes = base64Decoder.decodeBuffer(encodeString);
        System.out.println(new String(bytes, "UTF-8"));

    }


}
