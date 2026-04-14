package com.example.java_web;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取当前请求对应的 session（与登录时是同一个）
        HttpSession session = req.getSession(false); // false: 不存在则返回 null； true或空: 不存在则创建

        if (session != null) {
            session.invalidate(); // 销毁 session
        } else {
            // session 不存在
            System.out.println("session 不存在");
        }

        // 重定向到登录页
        resp.sendRedirect("form.jsp"); // 即使你销毁了 Session，访问 JSP 时又会创建一个新的
//        resp.sendRedirect("register.html");
    }
}