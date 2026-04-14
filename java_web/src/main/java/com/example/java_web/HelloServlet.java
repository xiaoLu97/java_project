package com.example.java_web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World! 你好，世界？";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String gender = req.getParameter("gender");
        String subject = req.getParameter("subject");
        String[] hobbies = req.getParameterValues("hobby");
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(confirmPassword);
//        System.out.println(gender);
//        System.out.println(subject);
//        System.out.println("爱好: " + (hobbies != null ? String.join(", ", hobbies) : "无"));


        // 跳转到index.jsp 并且将 username 数据传递到 index.jsp
//        req.setAttribute("username", username);
        // 转发到index.jsp
//        req.getRequestDispatcher("form.jsp").forward(req, resp);

        // 重定向到index.jsp
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        resp.sendRedirect("form.jsp");
    }

    public void destroy() {
    }
}