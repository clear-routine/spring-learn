[IDEA 如何创建多模块项目](../config/idea-multi-module-project.md)

# 使用 XML
项目的具体细节可以直接参考代码仓库里的 **spring-xml** 模块。这个模块通过**最基础、最直观的示例**，一步一步带你完成整体搭建。在此基础上，下面还会对其中**一些必要的代码进行讲解**，帮助你更好地理解实现思路。

## Application.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userRepository" class="com.tt.repository.UserRepository"/>

    <bean id="userService" class="com.tt.service.UserService">
        <property name="userRepository" ref="userRepository"/>
    </bean>

    <bean id="userController" class="com.tt.controller.UserController">
        <property name="userService" ref="userService"/>
    </bean>

</beans>
```

**标签概念解释：**

+ `<beans>`：是 `Spring` 的**根容器标签**，可以理解为整个 `Spring` 配置的“最外层容器”，所有的 `<bean>` 定义都必须写在它里面。
+ `<bean>`：用来声明一个由 `Spring` 管理的对象。
    - `id="userRepository"`：给这个 `bean` 取一个名字，后续可以通过 `ApplicationContext.getBean("userRepository")` 来获取它。
    - `class="com.example.UserRepository"`：表示这个 `bean` 要实例化的类，这里写的是**类的全限定类名**。
+ `<property>`：用于给 `bean` 内部的属性赋值，实现依赖注入。`Spring` 在创建 `UserService` 时，会自动调用 `setUserRepository(UserRepository userRepository)` 方法，这就是典型的 **setter 注入**。
    - `name="userRepository"`：对应的是 `UserService` 类中的属性名，通常是 `private UserRepository userRepository;`。
    - `ref="userRepository"`：用于引用另一个由 `Spring` 管理的 `Bean`，这里填写的就是前面定义的 `Bean ID`，比如 `userRepository`。

后续，`Spring` 就会**根据你定义的 XML 配置文件来梳理对象之间的关系**，在此基础上完成对象的创建、装配，并最终负责整个程序的启动和管理。

## Main

```java
package com.tt;

import com.tt.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Application.xml");

        UserController userController = context.getBean("userController", UserController.class);

        userController.handle();
    }
}
```

通过 `new ClassPathXmlApplicationContext("Application.xml")` 把对应的配置文件加载进 `Spring` 容器中，然后再通过 `getBean` 方法获取需要的 `bean`，最后调用其中定义的方法即可完成业务逻辑的执行。

# 带注解的组件类（不再用 XML）
注解用起来非常方便，几乎不需要再手动写太多配置代码了。比如，使用 **@Repository** 修饰的类会被 `Spring` 纳入统一管理，一般用于数据库操作相关的代码，如数据的增删改查；**@Service** 修饰的类同样会交由 `Spring` 容器管理，通常用来表示应用中的业务逻辑层；**@Autowired** 用于将 `Spring` 容器中的 `bean` 自动注入到对应的属性中，减少手动创建和维护对象的麻烦；而 **@Controller** 则用于标识控制层的类，被它修饰的类会被 `Spring` 自动管理，主要负责处理 `Web` 请求。

> 项目的具体细节可以直接参考代码仓库里的 **spring-annotation** 模块。

## AppConfig
```java
package com.tt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.tt")
public class AppConfig {
}
```

`@ComponentScan("com.tt")` 用于启用组件扫描，指定 `Spring` 扫描 `com.tt` 包及其子包，从而自动发现并注册组件。

在扫描过程中，`Spring` 会自动识别并将以下注解标注的类注册为 `bean`：

+ `@Component`（通用组件）。
+ `@Service`（业务逻辑层）。
+ `@Repository`（数据访问层）。
+ `@Controller`（控制层）。

同时，`Spring` 还会自动处理组件之间的依赖注入，例如使用 `@Autowired` 注解的依赖关系。

`@Configuration` 注解用于标识配置类，`Spring` 会自动将该类作为一个 `bean` 进行管理，并承担相应的职责。它通常会与像 `@ComponentScan()` 这样具有具体功能的注解搭配使用，以便自动扫描和注册组件。

## Main

```java
package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main - 应用程序入口
 * 
 * 使用 AnnotationConfigApplicationContext 加载基于 Java Config 的配置类
 * 与 XML 配置方式（ClassPathXmlApplicationContext）不同，这里使用注解配置方式
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserController controller =
                context.getBean(UserController.class);

        controller.handle();
    }
}
```

通过 `new AnnotationConfigApplicationContext(AppConfig.class)` 把对应的配置类加载进 `Spring` 容器中，然后再通过 `getBean` 方法获取需要的 `bean`，最后调用其中定义的方法即可完成业务逻辑的执行。

# Java Config

在方式二中，可以将扫描改为**搭配 `@Bean` 注解的手动注册方式**。同时，`UserRepository`、`UserService`、`UserController` 类上的所有注解都可以取消，包括依赖注入相关的注解也无需保留。

> 具体的代码实现细节可以直接查看 **spring-annotation-bean-config** 模块。

## AppConfig
```java
package com.tt.config;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * 注册 UserRepository Bean
     * 
     * @Bean 注解表示该方法返回的对象会被注册为 Spring 容器中的一个 Bean
     * 方法名 userRepository 会作为 Bean 的默认名称（也可以通过 @Bean("customName") 指定）
     * 
     * @return UserRepository 实例
     */
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    /**
     * 注册 UserService Bean
     * 
     * 在创建 UserService 时，通过调用 userRepository() 方法获取 UserRepository Bean
     * Spring 会确保 userRepository() 方法只被调用一次，后续调用会返回同一个实例（单例模式）
     * 
     * @return UserService 实例，已注入 UserRepository 依赖
     */
    @Bean
    public UserService userService() {
        UserService userService = new UserService();
        // 手动调用 userRepository() 方法获取 Bean 并注入
        userService.setUserRepository(userRepository());
        return userService;
    }

    /**
     * 注册 UserController Bean
     * 
     * 在创建 UserController 时，通过调用 userService() 方法获取 UserService Bean
     * 同样，Spring 会确保单例模式，多次调用 userService() 返回同一个实例
     * 
     * @return UserController 实例，已注入 UserService 依赖
     */
    @Bean
    public UserController userController() {
        UserController userController = new UserController();
        // 手动调用 userService() 方法获取 Bean 并注入
        userController.setUserService(userService());
        return userController;
    }
}
```

上面每一个 `@Bean` 方法，返回的对象都会被当作一个 `Bean` 注册到 `Spring` 容器中。
