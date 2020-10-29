package com.example.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-10-29 19:40
 */
public class ResponseServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("来到Response1");
        // 将请求重定向到地址2去
//        // 状态码改为302，表示重定向
//        response.setStatus(302);
//        // 设置响应头，说明 新的地址在哪里
//        response.setHeader("Location", "http://localhost:8080/web_servlet/response2");

        // 或者直接这样
        response.sendRedirect("http://localhost:8080/web_servlet/response2");
    }
}
