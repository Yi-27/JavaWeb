package com.example.cookie_session;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Yi-27
 * @create 2020-11-02 8:43
 */
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("这时Session会话的测试");

        // 获取Session
        HttpSession session = request.getSession(); // 这里由于刚启动程序时，直接跳转到工程页面，这时就有自动创建一个Session，因此如果不是上来直接访问/session，那么获取到的Session就是之前创建好的，而不是首次创建的
        // 向Session域中添加键值对
        System.out.println(session.getId());
        session.setAttribute("key1", "value1"); // 这样做并不会修改Id值
        System.out.println(session.getId());
        // 添加中文
        session.setAttribute("key2", "这时中文啊"); // Session 中可以直接添加中文
        session.setAttribute("key3", new BASE64Encoder().encode("这时一段中文".getBytes("UTF-8")));
        response.getWriter().write("Session的ID为：" + session.getId());


        // 测试Cookie
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        for (Cookie cookie : cookies) {
            System.out.println();
            System.out.println(cookie);
            System.out.println(cookie.getComment());
            System.out.println(cookie.getDomain());
            System.out.println(cookie.getMaxAge());
            System.out.println(cookie.getName());
            System.out.println(cookie.getSecure());
            System.out.println(cookie.getPath());
            System.out.println(cookie.getValue());
            System.out.println(cookie.getVersion());
            System.out.println();
            if ("cookie1".equals(cookie.getName())) {
                cookie.setValue("this new value");
                response.addCookie(cookie);
            }
        }

        response.setHeader("Set-Cookie", "zxc=newValueaaaa; vbn=eee; haha=xixi;"); // 需要指明Set-Cookie，浏览器才会保存这两个Cookie，但是浏览器只保存了第一个
        /*
        并且这里有个很严重的问题
        当我们，重新部署程序后，请求该页面，进入该程序，而这里使用了setHeader来设置Set-Cookie时，
            如果原本的浏览器中该页面的JSESSIONID（旧的）依旧存在，第一步获取getSession()是创建新的Session会话，但是由于这里的Set-Cookie就导致新创建的Session没有替换掉旧的Session，但是Set-Cookie中的值被添加进去了，哪怕是这是多请求几次，仍旧会新创建Session并无法替换掉旧的值
            不过如果是页面没有JSESSIONID（被删了或过期了），但是zxc这个Cookie还在。访问该页面，仍旧不会存入新的Session会话的id
            但是在zxc这个Cookie被删了，也没有JSESSIONID，那么访问该页面时，会首先存入新的Session会话id，同时zxc未被存进去。再次访问时，不会再创建新的Session，同时将zxc存进去
         在这种情况下，先访问别的页面来创建新的Session，就会避免这个问题
         */
    }
}
