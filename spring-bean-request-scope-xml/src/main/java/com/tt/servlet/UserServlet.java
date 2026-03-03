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
 * 用户接口：/user，演示 request 作用域（UserController scope="request"）
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        UserController controller = context.getBean(UserController.class);
        String result = controller.getUser();

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.println("request scope 实例 ID: " + controller.getInstanceId());
    }
}
