package com.example.ajax;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-11-04 20:41
 */
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是JQuery发来的Ajax请求");
        // 解决中文乱码问题
        // 这些在获取请求参数之前才有效
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        String d = request.getParameter("d");
        System.out.println(action + " === " + d);

        Person person = new Person(2, "嘿嘿嘿");

        // json格式字符串
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        // 将json字符串返回给前端
        response.getWriter().write(personJsonString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("收到Ajax的请求");
        // 解决中文乱码问题
        // 这些在获取请求参数之前才有效
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");


        Person person = new Person(1, "哈哈哈");

        // json格式字符串
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        // 将json字符串返回给前端
        response.getWriter().write(personJsonString);
    }
}
class Person{
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}