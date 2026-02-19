# 容器概述

`Spring` 提供了一个被称为“**总控制中心**”的接口 —— **ApplicationContext**。从本质上讲，它本身就是 **Spring 的 IoC 容器**。

你不需要自己去 `new` 对象，对象的创建全部交给 `ApplicationContext` 来完成；它不仅负责 **创建对象（`bean`）**，还会 **给对象设置属性、注入依赖**，并且把对象之间的关系提前“接好”。比如 **A 依赖 B，B 又依赖 C**，这些关系都会由它统一管理和装配完成。

一句话总结：**ApplicationContext 就是 Spring 的“对象工厂 + 调度中心”**，它负责创建 `bean`、配置 `bean`，并把各个 `bean` 之间的依赖关系完整地组织起来。

那 `Spring` **凭什么知道要创建哪些对象**呢？答案其实很简单，**容器是“按说明书办事的”**。

这本“说明书”是你写给 `Spring` 的，更准确地说，`Spring` 并不是凭空猜测，而是**读取你提供的配置说明**。正是通过解析这些配置，`Spring` 才知道：

+ 需要创建哪些对象。
+ 每个对象该如何配置。
+ 对象之间又该如何建立依赖关系。

换句话说，**Spring 做的所有事情，都是依据你事先写好的配置来执行的**。你负责“告诉它规则”，它负责“严格照着规则把事情做好”。

这本“说明书”的写法，**一共可以分为三大类**：

1. **带注解的组件类**：也就是通过各种注解直接标记在类上的方式，让 `Spring` 知道这是一个需要被管理的组件。
2. **带有工厂方法的配置类**：通常使用配置类加方法的形式，明确告诉 `Spring` 对象应该如何被创建，以及创建过程中需要哪些依赖。
3. **外部配置文件（比较老，但现在依然在用）：**`XML`**。**

`Spring` 支持多种“配置说明书”的写法，你可以根据自己的习惯或项目需要选择任何一种。无论是注解、配置类，还是外部配置文件，本质上做的都是同一件事，**把整个应用的对象创建规则和依赖关系描述清楚**。只要配置写完整，不管采用哪种方式，都可以把整个应用“搭”起来。

`ApplicationContext` 本身是一个接口，`Spring` 在核心模块里已经帮我们准备好了好几种现成可用的实现类。

在**独立运行的应用程序**（也就是普通的 `main` 方法）中，最常用的主要是下面这两种容器实现：

+ **AnnotationConfigApplicationContext。**
+ **ClassPathXmlApplicationContext。**

**AnnotationConfigApplicationContext**（注解方式，目前最常用），当你使用**配置类 + 注解**的方式来写 `Spring` 程序时，就用它来加载你的配置。

**ClassPathXmlApplicationContext**（`XML` 方式，偏老的传统方案），如果你是通过 **XML 文件** 来配置 `Spring`，那么就使用这个类从 `classpath` 中加载对应的 `XML` 配置文件。

[三种配置的编写方式](../../example/three-config-approaches.md)

在大多数应用场景下，其实**不需要手动编写** `AnnotationConfigApplicationContext`、`ClassPathXmlApplicationContext` 这一类显式实例化代码来创建 `Spring IOC` 容器。

比如在**普通的 Web 应用**中，就可以通过 **配置 **`**web.xml**` 的方式来**替代手动创建 Spring IOC 容器对象**。常见的组合方式有：

+ **XML 配置 + web.xml**（传统方式）。
+ **注解配置 + web.xml**（传统 `Spring MVC` 项目）。

而如果使用的是 **Spring Boot**，情况就更简单了，通常**连 **`**web.xml**`** 都不需要了**，基本上采用的就是**纯注解的开发方式**，`Spring Boot` 会在启动阶段自动帮你完成 `IOC` 容器和 `Web` 环境的初始化。

[XML 配置 + web.xml](../../example/xml-config-web-xml.md)

[注解配置 + web.xml](../../example/annotation-config-web-xml.md)

> `Spring Framework` 本身**只专注于 IoC 容器和 AOP**，并不提供对外通信的能力。如果需要让应用对外提供服务，就必须借助 **Servlet** 或其他 `Web` 容器技术来实现。
>

虽然 `Spring` 容器在技术上可以管理任何普通类，并将其注册为 `Bean`，但在实际开发中，我们通常只会把**真正参与系统运行和协作的业务组件**交给 `Spring` 管理。例如，服务层对象（`Service`）、持久层对象（`Repository / DAO`）、表示层对象（如 `Web Controller`），以及一些基础设施组件（如 `JPA` 的 `EntityManagerFactory`、`JMS` 队列等），这样可以让依赖注入和对象协作更加方便、清晰。

而实体类（`Entity`）一般不会交由 `Spring` 容器管理，因为它们本质上只是**数据的载体**，用于描述业务数据结构，本身不承载业务行为，也不直接参与业务逻辑的执行。将实体类纳入 `Spring` 管理不仅收益不大，反而可能增加理解和维护成本。

# 基于 XML 的配置元数据组合

项目规模变大后，如果把所有配置都堆在一个文件里，维护和排查问题会变得非常困难。为了解决这个问题，可以将 `XML` 中的 `Bean` 定义拆分到多个 `XML` 文件中。每个文件对应一个逻辑层或功能模块，结构更清晰，更容易维护和查找相关配置，团队协作时冲突更少、效率更高。

```xml
❌ 不好的方式（所有配置在一个文件）：
application.xml (1000 行)
├── User 相关配置
├── Product 相关配置
└── ... 所有配置混在一起

✅ 好的方式（按模块拆分）：
application.xml (主配置)
├── user-config.xml (用户模块配置)
├── product-config.xml (产品模块配置)
└── ... 每个配置单独编写
```

拆分以后，可以使用 `ClassPathXmlApplicationContext` 来加载对应的 `XML` 配置文件，这个类的构造方法支持同时传入多个 `XML` 文件路径，从而一次性加载多个配置文件。

[多模块 XML 配置](../../example/multi-module-xml-config.md)

除了直接加载多个 `XML` 配置文件之外，还可以使用 `<import />` 标签，在一个 `XML` 配置文件中引入其他 `XML` 配置文件，把多个配置合并在一起。这样一来，在 `web.xml` 中就不需要再逐个配置所有的 `XML` 文件了。

[多模块 XML 和主配置文件](../../example/multi-module-xml-and-main-config.md)

# 使用容器

`ApplicationContext` 中可以存放各种各样的对象，通过调用 `getBean` 方法即可获取对应的 `Bean` 实例。获取 `Bean` 的方式有多种，具体可以参考下面的代码示例。

[使用容器](../../example/using-container.md)

虽然 `Spring` 允许你直接从容器中获取 `bean`，但理想情况下，代码不应该直接调用它。`Spring` 的核心理念是 **依赖注入（DI）**—— 你的类无需自己去“查找”对象，`Spring` 会自动帮你注入依赖。比如，你可以通过注解（如 `@Autowired`）直接获取所需的 `bean`，这样代码无需依赖 `Spring API`，既更干净，也更易测试，实现了更好的解耦。