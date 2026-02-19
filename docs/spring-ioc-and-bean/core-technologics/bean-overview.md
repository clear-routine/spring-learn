# Bean 概述

`Spring IoC` 容器负责管理一个或多个 `Bean`（对象），那 Bean 到底是啥？说白了，Bean 的本质就是一个普通的 Java 对象，跟你平时写的 `new Xxx()` 出来的对象没啥区别。唯一的区别在于这个对象不是你自己 `new` 的，而是 Spring 帮你 `new` 的。

`Bean` 也不是凭空出现的，`Spring` 之所以知道要创建哪些对象、怎么创建，靠的是你提供给它的**配置信息**。以前大家常见的是 `XML` 里的 `<bean />`，现在更常见的是这些注解：

+ `@Component`。
+ `@Service`。
+ `@Bean`。

不管你用的是 `XML`、注解，还是 `Java Config`，本质都一样，**你在给 Spring 一份“配置元数据（**`**metadata**`**）”。**

那 `Spring` 内部是怎么“存”这些配置的呢？答案是**每一个 Bean，都会被转换成一个 **`BeanDefinition`** 对象。**

你可以把 `BeanDefinition` 理解成这个 `Bean` 的 **“说明书 / 档案”**，`Spring` 后面所有的创建、装配、管理行为，都是围绕这份档案来的。

在这个 `BeanDefinition` 里，`Spring` 会记录一个 `Bean` 从“出生”到“死亡”所需要知道的所有信息。

+ **这个 Bean 是用哪个类创建的？**`Spring` 至少得搞清楚一件事，“你到底是想让我 `new` 哪个类？”
+ 然后是一些**行为相关的配置**，也就是这个 `Bean` 在容器里“怎么活”，是单例（`singleton`）还是多例（`prototype`）？要不要执行初始化回调（`@PostConstruct`）？容器关闭时要不要执行销毁回调（`@PreDestroy`）？
+ 再然后，是**依赖关系**。比如 `OrderService` 类里面有一个私有属性 `private UserService userService;`，这里面的 `UserService` 对 `OrderService` 来说，就是依赖（`dependency`）、协作者（`collaborator`），`Spring` 需要记住一件关键的事，“我创建 `OrderService` 之前，必须先把 `UserService` 准备好。”
+ 最后，还有一些**业务相关的配置值**，比如：连接池大小、最大连接数、超时时间。这些看起来不像对象本身，但它们同样属于 `Bean` 的一部分 —— 都是这个 `Bean` 的“出生配置”。

所以总结一下，**Bean 并不神秘，就是普通 Java 对象；神秘的是 Spring 在背后用 **`BeanDefinition`**，把关于它的一切都提前记了下来，然后再按这份“说明书”去把对象完整、正确地造出来并管好。**

除了通过 `XML`、注解或配置类声明 `bean` 之外，你还可以在应用运行过程中，通过代码**动态地将 Bean 注册到 Spring 容器中**。`ApplicationContext` 内部实际上持有一个 `BeanFactory`，可以通过 `getAutowireCapableBeanFactory()` 方法获取到它，然后借助这个 `BeanFactory` 将对象注册进 `Spring` 容器。

在注册方式上，主要有两种：

+ `registerSingleton()`：将你已经手动 `new` 出来的对象实例直接注册为一个单例 `Bean`；
+ `registerBeanDefinition()`：仅注册 `Bean` 的定义信息，对象本身尚未创建，后续由 `Spring` 在合适的时机按照定义规则完成实例化和管理。

这种方式为 `Spring` 提供了更高的灵活性，但在一般的业务开发中，仍然以常规的 `Bean` 定义方式为主。

> 在正常的业务代码里千万别这么玩，这种能力主要是留给框架、中间件、容器扩展点以及 `Spring` 自己内部使用的，并不是给日常 `CRUD` 场景准备的，如果在业务代码中随意滥用，很容易让系统结构变得混乱，增加理解和维护成本，甚至埋下非常隐蔽的风险。
>

[动态加载 bean](../../example/dynamic-load-bean.md)

## Overriding Beans

**什么是 Bean 覆盖（Overriding Beans）？**简单说，就是**同一个 Bean 名字被定义了两次，后定义的会把前面的顶掉**。`Spring` 默认是允许这种行为的，但问题也很明显，**看配置的人很容易懵**，根本分不清运行时到底用的是哪个 `Bean`，一不小心就给自己埋坑了。官方也意识到这个问题，所以在**未来的 Spring 版本中，这种行为会被逐步“弃用”**。

如果你不希望项目里出现 Bean 覆盖，可以在 `ApplicationContext` 刷新时直接「硬性禁止」。你可以明确告诉 Spring，不允许 Bean 重名，谁敢重名就直接报错！

做法是通过 `ApplicationContext` 调用 `setAllowBeanDefinitionOverriding(false)`，这样一来，只要出现重名 Bean，容器启动直接失败并抛异常，强制你去改配置，问题不会被悄悄吞掉。

[禁止 Spring 的 Bean 覆盖行为](../../example/spring-bean-override-disabled.md)

如果你没有显式禁止 Bean 覆盖，那 Spring 在每次发生隐式的 Bean 覆盖时，都会在 Debug 级别打日志，提醒你「这里发生过覆盖」。虽然这样你有机会察觉到问题，但说实话，仍然不太安全，官方也更建议直接禁止。

[Spring 的默认 Bean 覆盖行为](../../example/spring-bean-override-default.md)

如果你是通过 `@Configuration + @Bean` 的方式来注入对象，@Bean 修饰的对象都会生效并覆盖同名对象。这是 `Spring` 官方刻意的设计，并不被认为是“非法覆盖”。原因在于 `Spring` 的设计取向，`@Bean` 属于**显式配置**，而组件扫描（`@Component` / `@Service` 等）只是一种**隐式的默认行为**，遵循的是“**显式 > 隐式**”的原则。

[@Bean 注解进行强行覆盖](../../example/bean-annotation-force-override.md)

## Naming Beans

每个 `Bean` 都必须有一个“名字”。`Bean` 本质上是 `Spring` 容器中的一个对象实例。可以把 `Spring` 容器理解成一个大仓库，里面存放着很多对象（也就是各种 `Bean`）。如果没有名字，就没办法区分这些不同的 `Bean`。

每个 `Bean` 至少有一个标识符，并且这个标识符在容器中必须是唯一的。通常情况下，一个 `Bean` 只有一个名字；如果存在多个名字，那么其他的名字就是这个 `Bean` 的“别名（`alias`）”。

在 `XML` 配置中，可以通过 `id` 和 `name` 属性为当前 `Bean` 指定标识符。`id` 属性只能指定一个名字，它是这个 `Bean` 的主标识符。`name` 属性则可以指定多个名字，多个名称之间可以用逗号（`,`）、分号（`;`）或空格分隔。

[bean 别名](../../example/bean-alias.md)

`Bean` 并不一定非要写 `id` 或 `name`。只有在你需要通过名称获取某个 `Bean` 时，才必须为它指定名字。如果是内部 `Bean`，或者通过自动装配（`autowiring`）的方式进行依赖注入，通常就不需要显式地为 `Bean` 指定名称。

`Bean` 的名字最好遵循 `Java` 实例变量的命名规则，即首字母小写的驼峰式（`camelCase`），例如 `loginController`，这样既美观又易读。在配置文件中，这样命名的 `Bean` 一眼就能看出它的作用，如果所有 `Bean` 都遵循相同的规则，无论是别人还是自己回头查看配置文件，都能一目了然。

当你使用注解对 `Bean` 进行标注时，`Spring` 会在扫描到对应类时，自动将其实例化并注入到容器中管理。此时，`Spring` 给 `Bean` 命名的规则也是默认取类名的首字母小写。但也有特殊情况，如果类名前两个字母都是大写，则保持原样，不做首字母小写，例如 `URLService` 的 `Bean` 名称仍然是 `URLService`。`Spring` 的自动 `Bean` 命名始终遵循 `java.beans.Introspector.decapitalize()` 的规则。

## Instantiating Beans

前面提到过，`Spring` 实例化 `Bean` 是通过 `BeanDefinition` 完成的，`BeanDefinition` 本质上可以理解为一份“配方”或“说明书”。

其中会描述：

+ 类名是什么。
+ 使用哪个构造方法。
+ 需要注入哪些依赖。
+ 是否指定了工厂方法。
+ 是否为单例（`scope`）。
+ 等其他配置信息。

需要注意的是，在这个阶段真正的对象还没有被创建，`BeanDefinition` 只是用于描述如何创建这个对象。

在编写 `XML` 配置文件时，我们通常会写上 `class="xxx.xxx.ClassName"`。那这个 `class` 属性到底是做什么用的？它的作用是什么？

其实，这个属性会在实例化 `Bean` 的时候发挥关键作用。它相当于告诉 `Spring`：“等会儿创建对象时，需要用到的类就在这里。” 也就是说，`class` 属性指定了要实例化的具体类，`Spring` 会根据这里提供的类信息创建对应的对象。

### 使用构造函数进行实例化

那什么叫“构造函数方式实例化”呢？其实就是最常见、最普通的情况，在 `class` 属性中直接写入目标类的全限定类名，`Spring` 会根据这个类名完成依赖注入。

这种方式占绝大多数使用场景。本质上可以理解为等价于我们在代码中使用 `new` 创建对象，只不过这个创建过程由 `Spring` 在容器内部帮我们完成了。

不过需要注意的是，在使用构造函数方式实例化时，`Spring` 常见的依赖注入方式有两种：**Setter 注入**和**构造器注入**。

+ **Setter 注入**：前面已经写过很多次了，需要提供无参构造方法，`Spring` 先通过无参构造方法创建对象，再通过 `set` 方法为属性赋值。
+ **构造器注入**：需要提供对应参数的构造方法，构造方法需要什么依赖，就在参数列表中声明什么参数，`Spring` 在创建对象时会直接把依赖通过构造器传入。

两种方式的核心区别就在于：依赖是通过 `set` 方法赋值，还是在对象创建时通过构造方法一次性注入完成。

[使用构造函数创建 Bean](../../example/bean-constructor-create.md)

### 通过静态工厂方法实例化

**用静态工厂方法（static factory method）创建 Bean**，也是 `Spring` 提供的另一种实例化方式，比直接构造器更灵活。

如果想使用这种方式创建 `Bean` 对象，需要在 `class` 属性指定的全限定类中，编写一个静态方法，例如 `createInstance()`，该方法返回一个对象实例（可以是同类，也可以是其他类的实例）。

在 `XML` 配置中，通过 `factory-method` 属性指定你编写好的静态方法名。`Spring` 在创建 `Bean` 时，就会调用这个方法来生成对象。

如果你希望在创建 `Bean` 时给工厂方法传递参数，`Spring` 也是支持的。只需要在 `XML` 配置文件中使用 `<constructor-arg value="xxx"/>` 指明要传递的参数，`Spring` 会自动匹配工厂方法的参数并将其传入。

[使用静态工厂方法创建 Bean](../../example/bean-static-factory-create.md)

### 通过实例工厂方法进行实例化

与静态工厂方法类似，**实例工厂方法**是通过已有的 `Bean` 中定义的方法来创建新的 `Bean`，而不是通过尚未创建的 `Bean` 的静态方法进行创建。

由于是调用已有 `Bean` 的实例方法来创建 `Bean`，因此在 `XML` 配置中可以将 `class` 属性留空。在 `factory-bean` 属性中指定要使用的已有 `Bean`，再通过 `factory-method` 属性设置工厂方法的名称即可。其他配置方式与静态工厂方法基本一致。

[使用实例工厂方法创建 Bean。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/lr9syyv8drtfxwms)