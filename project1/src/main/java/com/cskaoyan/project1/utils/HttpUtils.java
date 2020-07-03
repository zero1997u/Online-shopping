package com.cskaoyan.project1.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpUtils {
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();//获取请求体 转化为json
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        //输入流中将最多 length 个字节的du数据读入一个 byte 数组中
        while((length = inputStream.read(bytes))!=-1){
            //将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流
            outputStream.write(bytes,0,length);
        }
        //json字符串-》java对象
        return outputStream.toString("utf-8");

    }
}
