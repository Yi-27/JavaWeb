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
 * @create 2020-11-03 19:41
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 获取会话
        HttpSession session = request.getSession();
        // 获取验证码
        String kaptcha = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        // 删除验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        // 获取上一个页面
        String referer = (String) session.getAttribute("Referer");
        System.out.println(referer);


        //获取表单中的验证码是否正确
        String inputKaptcha = request.getParameter("kaptcha");
        // 比较输入验证码是否正确
        if(kaptcha != null && kaptcha.equalsIgnoreCase(inputKaptcha)){
            // 正确，则提取用户名和密码存储在Session中（这里假设输入的用户名和密码肯定正确，但是非空肯定不行）
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if("".equals(username) || "".equals(password)){
                response.getWriter().write("用户名或密码为空！5秒后返回重新登录");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("http://localhost:8080/web_servlet/login.html"); // 重定向回去
            }else{
                session.setAttribute("user", username); // 将用户记录在Session中
                response.getWriter().write("用户记录成功！");
                response.sendRedirect(referer);
            }
        }else{
            // 不正确，则返回不正确
            response.getWriter().write("验证码输入错误或过期或为空，请重新输入！5秒后返回重新登录");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            response.sendRedirect("http://localhost:8080/web_servlet/login.html"); // 重定向回去
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
