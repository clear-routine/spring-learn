# XML 配置 + web.xml

详细示例可以参考 **spring-xml-web** 模块。

## Pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>spring-container</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

</project>
```

这里的项目只能打成 **WAR 包**，而 `WAR` 包本身是**无法独立运行的**，必须依赖外部的 `Web` 容器才能启动，比如 `Tomcat`。原因在于 **Spring 本身只提供了 IOC 和 AOP 等核心功能，并不内置 Web 容器（如 Tomcat）**，因此项目只能以 `WAR` 形式部署到外部容器中运行。

[多模块项目如何配置 Tomcat。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/apukd055htx9bpq9)

## Servlet

```java
/**
 * 产品接口 Servlet：处理 HTTP 请求
 * 访问路径：/product
 * 
 * 架构：Servlet -> Controller -> Service -> Repository
 */
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 从 Spring 容器中获取 bean（容器已通过 ContextLoaderListener 自动创建）
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        
        // 从 Controller 获取数据（遵循 MVC 分层架构）
        ProductController controller = context.getBean(ProductController.class);
        String result = controller.getProduct();
        
        // 返回接口的返回值
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
```

```java
/**
 * 用户接口 Servlet：处理 HTTP 请求
 * 访问路径：/user
 * 
 * 架构：Servlet -> Controller -> Service -> Repository
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 从 Spring 容器中获取 bean（容器已通过 ContextLoaderListener 自动创建）
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        
        // 从 Controller 获取数据（遵循 MVC 分层架构）
        UserController controller = context.getBean(UserController.class);
        String result = controller.getUser();
        
        // 返回接口的返回值
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
```

**Servlet 中编写的代码，通常是最先被执行的。**当客户端请求到达服务器后，请求会首先进入 `Servlet`，由 `Servlet` 来接收并处理请求，执行对应的业务逻辑。在代码执行完成之后，服务器再将处理结果返回给客户端。

## web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <!-- 配置 Spring 应用上下文的配置文件位置 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:application.xml</param-value>
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

`<context-param>` 用来配置 **Spring 启动时需要加载的项目配置内容**。

+ `<param-name>` 表示**参数名称**，取值一般为**固定值**，这是 Spring 的约定。例如 `<param-name>contextConfigLocation</param-name>` 表示**当前项目中 Spring 配置文件的位置**。
+ `<param-value>` 需自行填写。例如 `classpath:application.xml` 表示 Spring 配置文件位于**类路径（classpath）下的 `application.xml` 文件**中。

通过这样的配置，`Spring` 在项目启动时就能够正确加载对应的配置文件。

---

`<listener>` 用于配置**监听器**，`<listener-class>` 指定具体的监听器实现类。项目启动后，`ContextLoaderListener` 会被触发执行，根据 `<context-param>` 中的配置加载 Spring 配置文件，**自动创建 `ApplicationContext` 容器**，并将 Spring 中定义的内容加载到容器中。

---

`<servlet>` 用于**定义 Servlet**，将 Servlet 与代码中的某个类绑定。当 Servlet 被调用时，会执行对应类中的逻辑。

+ `<servlet-name>`：为当前 Servlet 指定**唯一名称**；
+ `<servlet-class>`：指定 **Servlet 绑定的具体类**；

该标签主要用于**指定后端的处理逻辑**，为每个接口建立对应的**映射关系**。

---

`<servlet-mapping>` 用于将 Servlet 与**客户端请求路径**建立映射：

+ `<url-pattern>`：**对外暴露访问路径**，供客户端调用；
+ `<servlet-name>`：**指定该路径对应的 Servlet**。

当客户端请求匹配到该路径时，后端就会根据映射关系，调用对应的 `Servlet`，并执行其中的处理逻辑。

[多模块项目如何配置 Tomcat。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/apukd055htx9bpq9)