package com.tt.config;

import com.tt.servlet.UserServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * 方式二：WebApplicationInitializer 纯 Java 配置方式（推荐）
 *
 * 无需 web.xml，Spring 会在应用启动时自动发现并调用所有 WebApplicationInitializer 实现。
 * 在本类中通过 addListener 注册 RequestContextListener，实现请求到线程的绑定。
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        // 创建根应用上下文（注解配置）
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        // 注册配置类：使 Spring 加载 AppConfig，进而通过 @ComponentScan 发现并注册所有 Bean
        rootContext.register(AppConfig.class);
        // 启动时创建 Spring 根容器，加载 Bean
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // 方式二：RequestContextListener（纯 Java 配置）
        // 将当前 HTTP 请求绑定到处理线程，使 RequestContextHolder 在任意层级可获取当前请求
        servletContext.addListener(RequestContextListener.class);

        // 用户接口：/user
        registerServlet(servletContext, "UserServlet", UserServlet.class, "/user");
    }

    /** 注册 Servlet 并映射 URL */
    private void registerServlet(ServletContext ctx, String name, Class<? extends jakarta.servlet.Servlet> servletClass, String path) {

        ServletRegistration.Dynamic reg = ctx.addServlet(name, servletClass);
        reg.addMapping(path);
    }
}
