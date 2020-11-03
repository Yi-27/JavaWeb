package com.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Yi-27
 * @create 2020-11-03 16:27
 */
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * doFilter方法是专门用于拦截请求，过滤响应的。 可以做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 需要获取强转成子类对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取会话
        HttpSession session = httpServletRequest.getSession();
        // 提取 user 字段
        Object user = session.getAttribute("user");
        if (user == null){
            System.out.println("尚未登录");
            StringBuffer requestURL = httpServletRequest.getRequestURL();
            System.out.println(requestURL.toString());
            session.setAttribute("Referer", requestURL.toString());
            servletRequest.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
            return;
        }else{
            System.out.println("已登录继续！");
            // 让程序继续往下访问用户的目标资源
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
