package com.example.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-10-29 12:59
 */
@WebServlet(name = "DispatcherServlet2")
public class DispatcherServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取材料
        String username = request.getParameter("username");
        System.out.println("转发后的请求也能从中获取参数：" + username);

        // 检查是否盖章
        Object key1 = request.getAttribute("key1");
        System.out.println("柜台1的章为：" + key1);

        // 处理柜台2的逻辑
    }
}
