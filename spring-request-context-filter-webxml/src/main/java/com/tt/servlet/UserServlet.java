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
 * 用户接口 Servlet：架构 Servlet -> Controller -> Service -> Repository
 * 访问路径：/user
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = Objects.requireNonNull(getServletContext());
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext);
        UserController controller = context.getBean(UserController.class);
        String result = controller.getUser();

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
