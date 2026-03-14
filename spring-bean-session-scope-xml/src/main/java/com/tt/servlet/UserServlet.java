package com.tt.servlet;

import com.tt.controller.UserController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 用户接口：/user，演示 session 作用域（UserController scope="session"）
 * 同一会话内多次访问，instanceId 相同、visitCount 累加；不同会话（不同浏览器/无痕）则实例不同
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = Objects.requireNonNull(getServletContext());
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext);
        UserController controller = context.getBean(UserController.class);
        controller.incrementVisit();

        String result = controller.getUser();

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.println("session scope 实例 ID: " + controller.getInstanceId());
        out.println("本会话内访问次数: " + controller.getVisitCount());
    }
}
