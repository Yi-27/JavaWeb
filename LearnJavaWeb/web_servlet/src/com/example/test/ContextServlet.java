package com.example.test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-10-29 9:42
 */
public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是POST请求");
        ServletContext servletContext = getServletContext();

        servletContext.setAttribute("key1", "value1");
        System.out.println("servletContext.getAttribute(\"key1\") = " + servletContext.getAttribute("key1"));
        System.out.println("servletContext = " + servletContext);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是GET请求");
        // 获取ServletConfig
        ServletConfig servletConfig = getServletConfig();

        ServletContext servletContext = servletConfig.getServletContext();
        ServletContext servletContext1 = getServletContext(); // 这两个方法获取结果都是一样的

        // 获取该Servlet程序内的初始化参数
        System.out.println("servletConfig.getInitParameter(\"username\") = " + servletConfig.getInitParameter("username"));
        // 获取该Servlet的别名
        System.out.println("servletConfig.getServletName() = " + servletConfig.getServletName());

        // 获取全局的context参数
        System.out.println("servletContext.getInitParameter(\"password\") = " + servletContext.getInitParameter("password"));
        // 获取工程路径
        System.out.println("servletContext1.getContextPath() = " + servletContext1.getContextPath());
        // 获取部署时的绝对路径
        System.out.println("servletContext.getRealPath() = " + servletContext.getRealPath("/"));
        // 获取指定目录的绝对路径
        System.out.println("servletContext1.getRealPath() = " + servletContext1.getRealPath("/imgs/girl.jpeg"));

        // 获取域对象中的值
        System.out.println("servletContext.getAttribute(\"key1\") = " + servletContext.getAttribute("key1"));

    }
}
