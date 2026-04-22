package com.blog.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册功能后端实现
 * 对应前端 action="../registerServlet"
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 防止直接访问 Servlet 报错，统一交给 doPost 处理
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 设置编码，防止中文乱码（针对 Post 请求非常重要）
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取前端表单传递过来的参数
        // 这里的参数名必须和 HTML 中 input 的 name 属性完全一致
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 3. 简单的后端验证
        System.out.println("收到注册请求：");
        System.out.println("用户名: " + username);
        System.out.println("邮箱: " + email);

        if (username == null || username.trim().isEmpty()) {
            response.getWriter().write("<script>alert('用户名不能为空！'); history.back();</script>");
            return;
        }

        // 4. TODO: 这里后续对接数据库 JDBC 逻辑
        // 目前我们先模拟注册成功
        boolean isSuccess = true;

        if (isSuccess) {
            // 注册成功，跳转到登录页（根据你的目录结构，login.html 在根目录）
            // 注意：因为 Servlet 路径可能在 /user/ 下，建议用重定向
            response.sendRedirect(request.getContextPath() + "/login.html");
        } else {
            // 注册失败
            response.getWriter().write("<script>alert('注册失败，请稍后重试'); history.back();</script>");
        }
    }
}