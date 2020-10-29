package com.example.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-10-29 11:07
 */
@WebServlet(name = "RequestAPIServlet")
public class RequestAPIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("URI：" + request.getRequestURI()); // 获取请求的资源路径
        System.out.println("URL：" + request.getRequestURL()); // 获取请求的统一资源定位符
        /*
        这里由于使用localhost或者127.0.0.1 访问所以 IP：127.0.0.1
        用真实的就是真实的
         */
        System.out.println("IP：" + request.getRemoteHost()); // 获取客户端的IP地址
        System.out.println("Request Header：" + request.getHeader("User-Agent")); // 获取请求头
        System.out.println("Method：" + request.getMethod()); // 获取请求的方式
    }
}
