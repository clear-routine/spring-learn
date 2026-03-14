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
 * JDK 动态代理：proxy-target-class="false"
 *
 * UserService 实现接口，Spring 生成 Proxy 实现该接口，UserController 通过接口类型注入
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ServletContext servletContext = Objects.requireNonNull(getServletContext());
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        UserController controller = context.getBean("userController", UserController.class);

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(controller.getUser());
        out.println("userService (session, JDK 代理) 实例 ID: " + controller.getUserServiceInstanceId());
        out.println("Session ID: " + request.getSession().getId());
    }
}
