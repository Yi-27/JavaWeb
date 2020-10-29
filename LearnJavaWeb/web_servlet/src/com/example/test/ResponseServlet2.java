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
public class ResponseServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 放置中文乱码
        response.setContentType("text/html; charSet=UTF-8");
        System.out.println(response.getCharacterEncoding());
        System.out.println(request.getCharacterEncoding());
        response.getWriter().write("重定向到2这里");
    }
}
