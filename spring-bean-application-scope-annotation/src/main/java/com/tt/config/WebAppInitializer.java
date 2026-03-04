package com.tt.config;

import com.tt.servlet.ProductServlet;
import com.tt.servlet.UserServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.setServletContext(servletContext);
        rootContext.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        registerServlet(servletContext, "UserServlet", UserServlet.class, "/user");
        registerServlet(servletContext, "ProductServlet", ProductServlet.class, "/product");
    }

    private void registerServlet(ServletContext ctx, String name, Class<? extends jakarta.servlet.Servlet> servletClass, String path) {

        ServletRegistration.Dynamic reg = ctx.addServlet(name, servletClass);
        reg.addMapping(path);
    }
}
