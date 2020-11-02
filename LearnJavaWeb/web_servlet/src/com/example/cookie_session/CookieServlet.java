package com.example.cookie_session;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-11-02 8:56
 */
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("这时对于Session会话的测试");

        // 获取Session
        HttpSession session = request.getSession();
        Object key1 = session.getAttribute("key1");
        response.getWriter().write("Session的ID为：" + session.getId());
        response.getWriter().write("</br>Session的key1为：" + key1);

        // 获取Session中中文的字段
        Object key2 = session.getAttribute("key2");
        response.getWriter().write("</br>Session的key2为：" + key2);
        Object key3 = session.getAttribute("key3");
        response.getWriter().write("</br>Session的key3为：" + new String(new BASE64Decoder().decodeBuffer((String) key3), "UTF-8"));


        // 这里如果在没添加Session域中的值就直接想要获取其内的数据，会返回null。然后导致下面addCookie()和setHeader()都失效
        // 但是设置新的SessionID是成功的（重新部署或在Session在浏览器中过期和被删除的情况下）


        // 设置Cookie
        Cookie cookie1 = new Cookie("cookie1", "abc");
//        Cookie cookie2 = new Cookie("cookie2", "这是中文"); // Cookie的值不能是中文，并且这样做会导致下面能添加的代码页执行不了
        Cookie cookie3 = new Cookie("cookie3", new BASE64Encoder().encode("这时一段中文".getBytes("UTF-8")));

        response.addCookie(cookie1);
        response.addCookie(cookie3);

//        response.setHeader("Set-Cookie", "zxc=newValueaaaa; vbn=eee; haha=xixi;"); // 需要指明Set-Cookie，浏览器才会保存这两个Cookie，但是浏览器只保存了第一个，后续的都不会保存进去
        response.setHeader("Set-Cookie", "vbn=eee; haha=xixi;"); // 这样前面的zxc就会被覆盖点

   }
}
