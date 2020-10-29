package com.example.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Yi-27
 * @create 2020-10-29 12:17
 */
public class ParameterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码，先将字符集改为UTF-8
        request.setCharacterEncoding("UTF-8"); // 该步骤必须放在获取请求参数之前

        System.out.println("request.getParameter(\"password\") = " + request.getParameter("password"));
        System.out.println("request.getParameter(\"username\") = " + request.getParameter("username"));
        // 提取多个值
        System.out.println("Arrays.asList(request.getParameterValues(\"hobby\")) = " + Arrays.asList(request.getParameterValues("hobby")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
