package com.tt.config;

import com.tt.servlet.UserServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * 纯 Java 配置：request 作用域依赖 RequestContextListener 绑定请求
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        servletContext.addListener(RequestContextListener.class);

        registerServlet(servletContext, "UserServlet", UserServlet.class, "/user");
    }

    private void registerServlet(ServletContext ctx, String name, Class<? extends jakarta.servlet.Servlet> servletClass, String path) {

        ServletRegistration.Dynamic reg = ctx.addServlet(name, servletClass);
        reg.addMapping(path);
    }
}
