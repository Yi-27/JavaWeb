package com.example.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Yi-27
 * @create 2020-10-29 19:15
 */
@WebServlet(name = "ResponseIOServlet")
public class ResponseIOServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取服务端默认字符集
        System.out.println(response.getCharacterEncoding());

        // 设置服务器字符集为UTF-8
//        response.setCharacterEncoding("UTF-8");

        // 通过响应头，设置浏览器也使用UTF-8字符集
//        response.setHeader("Content-Type", "text/html; chartSet=UTF-8");

        // 或者这样，这样会同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头
        response.setContentType("text/html; charset=UTF-8");
        System.out.println(response.getCharacterEncoding()); // UTF-8
        // 要求：往客户端回传 字符串 数据
        PrintWriter writer = response.getWriter();
        writer.write("这是回传的字符串");

    }
}
