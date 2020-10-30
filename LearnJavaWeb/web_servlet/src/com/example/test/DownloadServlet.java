package com.example.test;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yi-27
 * @create 2020-10-30 20:36
 */
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 获取下载文件名
        String fileName = "girl.png";
        // 2. 构建文件路径
        // 获取当前工程路径
        ServletContext servletContext = getServletContext();
        // 获取下载的文件类型
        String mimeType = servletContext.getMimeType("/imgs/" + fileName);
        System.out.println("下载类型为：" + mimeType);
//        String realPath = servletContext.getRealPath("/");

        // 在回传钱，通过响应头告诉客户端返回的数据类型
        response.setContentType(mimeType);
        // 5. 并且告诉客户端要用于下载使用（使用响应头）
        // Content-Disposition；表示下载的使用
        // attachment表示附件，用于下载使用
        // filename= 表示指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 获取资源像流一样的返回
        /*
            / 斜杠 被服务器解析表示地址为：http://ip:port/工程名/ 映射到 web目录
            直接这样写就直接获取到输入流了
         */
        InputStream resourceAsStream = servletContext.getResourceAsStream("/imgs/" + fileName);



        // 拼接文件路径
//        File file = new File(realPath + "/imgs/" + fileName);
        // 3. 将输入流转为输出流
        // 输入流
//        FileInputStream fileInputStream = new FileInputStream(file);
        // 输出流
        ServletOutputStream outputStream = response.getOutputStream();
//        IOUtils.copy(fileInputStream, outputStream);
        IOUtils.copy(resourceAsStream, outputStream); // 读取输入流的数据复制给输出流输出

    }
}
