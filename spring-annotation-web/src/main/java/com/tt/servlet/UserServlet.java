package com.tt.servlet;

import com.tt.controller.UserController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户接口 Servlet：处理 HTTP 请求
 * 访问路径：/user
 * 
 * 架构：Servlet -> Controller -> Service -> Repository
 * 
 * Servlet 中编写的代码作用：
 * - Servlet 是 Java Web 应用中最先被执行的代码
 * - 当客户端请求到达服务器后，请求会首先进入 Servlet
 * - 由 Servlet 来接收并处理请求，执行对应的业务逻辑
 * - 在代码执行完成之后，服务器再将处理结果返回给客户端
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 从 Spring 容器中获取 bean（容器已通过 ContextLoaderListener 自动创建）
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        
        // 从 Controller 获取数据（遵循 MVC 分层架构）
        UserController controller = context.getBean(UserController.class);
        String result = controller.getUser();
        
        // 返回接口的返回值
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
