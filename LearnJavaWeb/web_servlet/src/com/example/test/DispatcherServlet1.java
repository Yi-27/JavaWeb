package com.example.test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-10-29 12:58
 */
public class DispatcherServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 防止中文乱码

        // 获取请求的参数
        String username = request.getParameter("username");
        System.out.println("在柜台1处获取到的请求参数为：" + username);

        // 处理逻辑并盖章盖章
        request.setAttribute("key1", "这时柜台1的章");

        // 创建转发路径对象
        /*
        请求转发必须要以斜杠打头，斜杠表示：http://ip:port/工程名/，映射到IDEA工程路径
         */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/dispatcher2");

        // 转发请求
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 防止中文乱码

        // 获取请求的参数
        String username = request.getParameter("username");
        System.out.println("在柜台1处获取到的请求参数为：" + username);

        // 处理逻辑并盖章盖章
        request.setAttribute("key1", "这时柜台1的章");

        // 创建转发路径对象
        /*
        请求转发必须要以斜杠打头，斜杠表示：http://ip:port/工程名/，映射到IDEA工程路径
         */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/dispatcher2");

        // 转发请求
        requestDispatcher.forward(request, response);
    }
}
