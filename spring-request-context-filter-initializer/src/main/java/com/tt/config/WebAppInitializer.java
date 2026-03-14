package com.tt.config;

import com.tt.servlet.UserServlet;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.EnumSet;

/**
 * RequestContextFilter 编程方式配置
 *
 * 无需 web.xml，通过 addFilter 注册 RequestContextFilter，
 * 效果与 RequestContextListener 完全一致，Listener 注册有问题时可用 Filter 替代。
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(@NonNull ServletContext servletContext) throws ServletException {

        // 创建根应用上下文（注解配置）
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        // 注册配置类：使 Spring 加载 AppConfig，进而通过 @ComponentScan 发现并注册所有 Bean
        rootContext.register(AppConfig.class);
        // 启动时创建 Spring 根容器，加载 Bean
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // RequestContextFilter（编程方式）：将当前 HTTP 请求绑定到处理线程
        // addFilter：注册 Filter，对应 web.xml 的 <filter>
        // addMappingForUrlPatterns(dispatcherTypes, isMatchAfter, urlPatterns)：
        //   - EnumSet.allOf(DispatcherType.class)：对 REQUEST/FORWARD/INCLUDE/ERROR/ASYNC 等所有分发类型生效
        //   - false：插入到现有 mapping 之前（优先生效）
        //   - "/*"：匹配所有 URL 路径
        servletContext.addFilter("requestContextFilter", RequestContextFilter.class)
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

        // 用户接口：/user
        registerServlet(servletContext, "UserServlet", UserServlet.class, "/user");
    }

    /** 注册 Servlet 并映射 URL */
    private void registerServlet(ServletContext ctx, String name,
            Class<? extends jakarta.servlet.Servlet> servletClass, String path) {

        ServletRegistration.Dynamic reg = ctx.addServlet(name, servletClass);
        reg.addMapping(path);
    }
}
