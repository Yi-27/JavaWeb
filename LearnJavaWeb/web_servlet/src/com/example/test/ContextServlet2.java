package com.example.test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-10-29 10:06
 */
public class ContextServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是Context2的GET请求");
        ServletContext servletContext = getServletContext();
        System.out.println("servletContext.getAttribute(\"key1\") = " + servletContext.getAttribute("key1"));
        System.out.println("servletContext = " + servletContext);
    }
}
