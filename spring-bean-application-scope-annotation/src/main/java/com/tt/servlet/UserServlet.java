package com.tt.servlet;

import com.tt.config.ApplicationScopeBootstrap;
import com.tt.controller.UserController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户接口：/user，不通过 IOC，直接从 ServletContext.getAttribute 获取 application scope bean
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserController controller = (UserController) getServletContext().getAttribute(ApplicationScopeBootstrap.ATTR_KEY);
        if (controller == null) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().println("userController 未找到，若为 singleton 则不会放入 ServletContext");
            return;
        }

        controller.incrementVisit();
        String result = controller.getUser();

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.println("application scope 实例 ID: " + controller.getInstanceId());
        out.println("全应用访问次数: " + controller.getVisitCount());
    }
}
