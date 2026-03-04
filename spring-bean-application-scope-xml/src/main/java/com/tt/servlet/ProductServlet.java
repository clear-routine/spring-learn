package com.tt.servlet;

import com.tt.controller.ProductController;
import com.tt.config.ProductConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 产品接口：/product，每次请求 new 一个 ApplicationContext，从「新容器」拿 singleton
 * 不同容器 = 不同 singleton 实例，体现 singleton 是「每个 ApplicationContext 一份」
 * 与 application scope（每个 ServletContext 一份）形成对比
 */
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
        try {
            ProductController controller = context.getBean(ProductController.class);
            controller.incrementAccess();

            String result = controller.getProduct();

            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(result);
            out.println("singleton（新容器）实例 ID: " + controller.getInstanceId());
            out.println("本容器内访问次数: " + controller.getAccessCount());
        } finally {
            context.close();
        }
    }
}
