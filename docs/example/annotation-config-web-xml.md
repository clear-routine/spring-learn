# 注解配置 + web.xml

具体实现代码可以参考 `spring-annotation-web` 模块。

# web.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <!-- 配置 Spring 使用注解配置方式 -->
    <context-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
    </context-param>
    
    <!-- 配置 Spring 配置类的位置 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>com.example.config.AppConfig</param-value>
    </context-param>

    <!-- Spring 的上下文加载监听器，会自动创建 ApplicationContext -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!-- 用户接口：/user -->
    <servlet>
        <servlet-name>UserServlet</servlet-name>
        <servlet-class>com.example.servlet.UserServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>UserServlet</servlet-name>
        <url-pattern>/user</url-pattern>
    </servlet-mapping>

    <!-- 产品接口：/product -->
    <servlet>
        <servlet-name>ProductServlet</servlet-name>
        <servlet-class>com.example.servlet.ProductServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>ProductServlet</servlet-name>
        <url-pattern>/product</url-pattern>
    </servlet-mapping>

</web-app>
```

注意⚠️：对于使用注解方式配置的 `Spring` 项目，需要显式指定 `contextClass` 为`AnnotationConfigWebApplicationContext`。否则，`Spring` 在启动时会默认使用 `XmlWebApplicationContext` 来加载配置，而它期望的是 `XML` 配置文件，而不是 `AppConfig` 这样的 `Java` 配置类，最终就会导致项目启动失败。