package com.example.cookie_session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Yi-27
 * @create 2020-11-02 20:40
 */
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 获取会话
        HttpSession session = request.getSession();
        // 获取验证码
        String kaptcha = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        // 删除验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        // 获取表单中的验证码
        String inputKaptcha = request.getParameter("kaptcha");
        // 比较二者是否正确，这里由于可能存在
        System.out.println("验证码:" + kaptcha + " , " + inputKaptcha);
        if (kaptcha != null && kaptcha.equalsIgnoreCase(inputKaptcha)){
            response.sendRedirect(request.getContextPath() + "/d.html");
        }else {
            System.out.println("验证码错误或者过期");
            response.getWriter().write("<h1>验证码错误或者过期</h1>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
