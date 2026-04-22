package com.blog.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 实现了全链路编码控制的 Servlet
 * 解决乱码问题的同时，实现前后台差异化显示
 */
public class TestServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        // 后台初始化提示
        System.out.println(">>> [系统通知] TestServlet 编码增强版已就绪！");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        // --- 核心修复：乱码克星 ---

        // 1. 设置【请求】编码：解决 req.getParameter 获取到的中文乱码
        req.setCharacterEncoding("UTF-8");

        // 2. 设置【响应】内容类型和编码：解决浏览器显示中文乱码
        res.setContentType("text/html;charset=UTF-8");

        // --- 数据处理 ---

        // 接收来自浏览器的参数
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        String qq = req.getParameter("qq");

        // --- 网页端：友好显示 ---

        PrintWriter out = res.getWriter();
        out.println("<html><body style='font-family: Microsoft YaHei; text-align: center; padding-top: 50px;'>");

        if (name != null && !name.trim().isEmpty()) {
            out.println("<h1 style='color: #2c3e50;'>你好！" + name + "</h1>");
        } else {
            out.println("<h1 style='color: #7f8c8d;'>你好，神秘的访客！</h1>");
        }

        out.println("<p style='color: #95a5a6;'>后台已成功记录您的访问信息。</p>");
        out.println("</body></html>");

        // --- 后台控制台：专业报告 ---

        // 使用特殊的分割线，让报告在日志中一目了然
        System.out.println("\n****************************************");
        System.out.println("       后台接收数据报告          ");
        System.out.println("****************************************");
        System.out.println("  [用户姓名]: " + (name != null ? name : "未填写"));
        System.out.println("  [用户密码]: " + (pwd != null ? pwd : "未填写"));
        System.out.println("  [Q Q号码]: " + (qq != null ? qq : "未填写"));
        System.out.println("****************************************\n");
    }

    @Override public ServletConfig getServletConfig() { return this.config; }
    @Override public String getServletInfo() { return "Enhanced Encoding Servlet"; }
    @Override public void destroy() {
        System.out.println(">>> TestServlet 任务完成，正在安全退出。");
    }
}