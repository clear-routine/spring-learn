package com.tt.config;

import com.tt.controller.UserController;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 启动时触发 application scope bean 创建，并显式放入 ServletContext 供 UserServlet 获取
 *
 * Spring 的 application scope 可能使用内部 key 存储，UserServlet 的 getAttribute("userController") 拿不到。
 * 本类在 getBean 后，显式 setAttribute("userController", bean)，确保非 IOC 代码能通过固定 key 获取。
 */
@Component
public class ApplicationScopeBootstrap implements ApplicationContextAware, ServletContextAware, InitializingBean {

    public static final String ATTR_KEY = "userController";

    private ApplicationContext applicationContext;

    private ServletContext servletContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    @Override
    public void setServletContext(@NonNull ServletContext servletContext) {

        this.servletContext = servletContext;
    }

    @Override
    public void afterPropertiesSet() {

        UserController controller = applicationContext.getBean(UserController.class);
        servletContext.setAttribute(ATTR_KEY, controller);
    }
}
