package com.tt.servlet;

import com.tt.controller.UserController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Servlet -> Controller -> Service -> Repository，返回 TT
 *
 * 验证 scoped-proxy：UserController (singleton) 注入 UserService (session) 的代理
 * 同 session 内 userService 实例 ID 相同，不同 session 不同
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ServletContext servletContext = Objects.requireNonNull(getServletContext());
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext);

        UserController controller = context.getBean("userController", UserController.class);

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(controller.getUser());
        out.println("userService (session) 实例 ID: " + controller.getUserServiceInstanceId());
        out.println("Session ID: " + request.getSession().getId());
    }
}
