# 依赖注入

依赖注入（`DI`）其实说白了就是你需要用到的对象，不是自己在类里面 `new` 出来，而是由外部主动“塞”给你。也就是说，你不用关心它怎么创建，只管用就行，创建和管理对象这件事交给别人去做。

那什么叫“依赖”呢？我们来看一段代码。假设有这样一个类：

```typescript
class OrderService {
    private userService = new UserService();
}
```

从这段代码可以看出，`OrderService` 里面用到了 `UserService`。因为它需要调用 `UserService` 的功能，所以我们就说，`OrderService` 依赖 `UserService`。换句话说，`UserService` 就是 `OrderService` 的“依赖”。

在上面的代码里，`OrderService` 是自己去 `new UserService()` 的。也就是说，它不仅在“使用”这个依赖，还在“负责创建”这个依赖。

这样写的问题在于，两个类之间绑得特别紧。因为 `OrderService` 直接依赖了 `UserService` 的具体实现，一旦 `UserService` 发生变化（比如构造函数变了、实现方式变了，或者你想换成别的实现），`OrderService` 也很可能要跟着改，代码的耦合度会变高。而且，从使用角度来看，每个用到依赖的地方都要手动 `new` 一次，代码会越来越繁琐。

那依赖注入到底是怎么做的？我们来看下面这个类的写法。

```java
class OrderService {

    private UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }
}
```

和前面的写法对比一下，你会发现一个很关键的变化，`OrderService` 不再自己 `new UserService()` 了，而是把 `UserService` 作为参数，通过构造函数“接收”进来。

是谁把 `UserService` 传进来的？在使用 `Spring Framework` 的情况下，是 **Spring 容器** 负责这件事。当容器创建 `OrderService` 这个 `Bean` 的时候，会自动找到它所需要的 `UserService`，然后把它“注入”进去。这整个过程就叫做 —— **依赖注入（Dependency Injection，DI）**。

使用 `Spring Framework` 容器进行 `Bean` 管理的过程也称为**“控制反转（IoC，Inversion of Control）”**。原本是类自己控制对象的创建，现在改为由容器来控制，这就叫做控制权发生了反转。类不再自己创建对象，也不再掌握创建过程，而是把控制权交给了容器。

依赖的注入方式主要有两种，下面会分别进行介绍。第一种是构造函数注入（推荐使用），第二种是 `Setter` 注入。

# 基于构造函数的依赖注入

构造函数注入，本质上就是容器帮你调用构造函数，并把依赖作为参数传进去，我们来看下面这个类。

```java
public class SimpleMovieLister {
    private MovieFinder movieFinder;

    public SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
```

在创建 `SimpleMovieLister` 对象时，容器会调用 `SimpleMovieLister(MovieFinder movieFinder)` 这个构造方法，并把 `movieFinder` 作为参数传入。这个 `movieFinder` 并不是在类内部 `new` 出来的，而是容器提前创建好的 `Bean`，然后注入进来。

这里其实还能延伸出一种方式 —— 使用静态工厂方法创建 `Bean`。从原理上看，使用静态工厂方法创建 `Bean` 和直接使用构造函数创建 `Bean` 基本是等效的。可以看下面这段代码：

```java
public class SimpleMovieLister {

    private final MovieFinder movieFinder;

    private SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public static SimpleMovieLister create(MovieFinder finder) {
        return new SimpleMovieLister(finder);
    }
}
```

两者的核心逻辑是一样的，依然是把 `MovieFinder` 作为参数传入。唯一的区别在于，`Spring` 不再直接调用构造方法，而是通过调用这个静态方法来创建对象。对象创建完成后，再交由容器进行统一管理。

## 构造函数参数解析

当 `Spring` 容器创建一个 `Bean` 时，如果这个 `Bean` 的构造函数有参数，容器就需要确定每个参数对应哪个 `Bean` 或具体的值。这个过程就叫做 **构造函数参数解析**。

简单来说，就是 `Spring` 会把你在 `<constructor-arg/>` 中配置的内容，和构造函数的参数一一对应起来。

[使用构造函数创建 Bean](../../../example/bean-constructor-create.md)

上面这个示例是最简单的情况，因为构造函数只有一个参数，所以匹配起来非常直接。但是一旦参数多了，`Spring` 就可能会出现歧义。为了确保匹配正确，你需要做一些额外的限制。`Spring` 默认的匹配方式是 **顺序匹配**，如果不指定任何信息，它会假设第一个 `<constructor-arg>` 对应第一个参数，第二个对应第二个参数，以此类推。这就是默认的顺序匹配方式。为了提高可读性和语义清晰度，你可以使用 `name` 属性来明确指定参数名称。当然，`Spring` 还提供了 `type` 匹配和 `index` 匹配的方式，但实际上用得不多。

[使用 name 进行匹配](../../../example/constructor-arg-name-match.md)

# 基于 Setter 方法的依赖注入

基于 `Setter` 方法的依赖注入是这样实现的，`Spring` 容器会先实例化你的 `Bean`，也就是说先调用无参构造函数或无参静态工厂方法，创建一个“空壳”对象 —— 此时对象还没有依赖被填充。之后，容器再通过调用 `Setter` 方法，把对应的依赖注入进去，把“空壳”变成完整可用的对象。

> 其实前面我们展示的很多例子，本质上都是通过 **Setter 方法** 来进行依赖注入的。
>

# 依赖解析过程

`Spring Framework` 容器（`ApplicationContext`）在启动时会读取各种配置，这些配置可以来自 `XML` 文件、`Java` 配置类（使用 `@Configuration` 和 `@Bean` 注解）、或者直接扫描类上的注解（如 `@Component`、`@Service` 等）。通过这些配置，容器就能知道系统中有哪些 `Bean`，以及它们各自的依赖关系。

对于每个 `Bean`，无论是构造函数参数（`Constructor DI`）还是 `Setter` 方法（`Setter DI`）声明的依赖，容器在创建 `Bean` 的过程中，都会自动把这些依赖注入进去。

如果依赖的是普通值类型（比如 `int`、`boolean`、`String` 等），`Spring` 会自动进行 **类型转换**，因为在 `XML` 配置中，这些值通常都是以字符串形式书写，而 `Java` 里对应的类型可能并不是字符串，所以需要转换。例如 `<constructor-arg name="ultimateAnswer" value="42"/>` 会被转换成 `int` 类型。如果依赖的是另一个 `Bean`，`Spring` 就直接把那个 `Bean` 的引用注入进去，不需要做类型转换。

需要注意的是，`Bean` 并不一定在程序启动时就立刻创建。对于作用域为单例且设置为预实例化（默认）的 `Bean`，会在容器创建时立即实例化；而其他 `Bean` 则是在第一次被请求时才会创建。创建一个 `Bean` 时，可能会触发它的依赖项以及依赖项的依赖项依次被创建并注入。如果依赖配置有问题，比如类型不匹配或者缺少某个 `Bean`，错误不会在容器启动时出现，而是在首次创建有问题的 `Bean` 时才会报错。

## 循环依赖

循环依赖是指两个或多个 `Bean` 互相依赖，形成一个闭环。如果使用构造函数注入，就可能遇到无法解决的循环依赖问题，比如 `Spring` 先创建 `A`，但 `A `需要 `B`；然后创建 `B`，而 `B` 又依赖 `A`，这样就永远无法完成对象的创建，最终会报错 `BeanCurrentlyInCreationException`。

构造函数注入容易出问题的原因在于，它要求所有依赖在对象创建时就必须提供。在循环依赖的情况下，`Spring` 无法先创建 `A` 再创建 `B`，也无法先创建 `B` 再创建 `A`，就形成了典型的“鸡生蛋 / 蛋生鸡”问题，因此无法解决。

为了解决循环依赖，可以将部分依赖的注入方式从构造函数改为 `Setter` 方法。通过 `Setter` 注入，`Spring` 先用无参构造函数创建一个“空壳”对象，然后再通过 `Setter` 方法注入依赖。因为对象的创建不再依赖于立即存在的依赖项，这样循环依赖就可以被顺利解决。

如果不存在循环依赖，假设 `Bean A` 依赖 `Bean B`，`Spring` 会先把 `Bean B` 完全初始化好，然后再注入到 `Bean A`。这里的“完全初始化”包括，实例化 `Bean`（如果它不是已经预实例化的单例）、注入 `Bean B` 的依赖、调用生命周期方法（如 `@PostConstruct`、`InitializingBean.afterPropertiesSet()` 或自定义的 `init` 方法）。这就是 `Spring` 创建 `Bean` 时的先后顺序机制。