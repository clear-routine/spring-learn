# 依赖与配置

# XML 文件里面配置的基本数据类型是如何进行注入的？

在 `XML` 里给 `Bean` 的属性赋值时，其实 **XML 本身是没有 int、boolean、Class 这些类型概念的**，在 `XML` 里看到的统统都是字符串。例如，`<property name="username" value="root"/>`，这里的 `"root"` 本质上就是一个字符串而已。

那问题来了，**Spring 是怎么知道这个属性真正的类型是什么**？答案是 `Spring` 会根据目标属性的类型，自动把字符串转换成对应的真实类型，然后再注入进去。

来看一个例子：

```java
public class User {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }
}
```

如果在 `XML` 里这样写：

```xml
<property name="age" value="18"/>
```

虽然 `"18"` 在 `XML` 里只是一个字符串，但 `Spring` 在运行时会：

1. 发现 `age` 对应的是 `int` 类型。
2. 自动把 `"18"` 转换成整数 `18`。
3. 然后调用 `setAge(18);`。

整个过程是自动完成的。

所以，`Spring XML` 注入的本质其实非常简单：

+ 你负责写字符串。
+ `Spring` 负责做类型转换。
+ `Spring` 再调用对应的 `setter` 方法。

说白了，`XML` 只是配置的载体，真正懂“类型”的，是 `Spring`。

## p-namespace

`<property>` 标签其实还有一种更简洁的写法，叫 `p-namespace`（`p` 命名空间），我们先看一个常规写法。

```xml
<bean id="myDataSource"
      class="org.apache.commons.dbcp.BasicDataSource"
      destroy-method="close">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="misterkaoli"/>
</bean>
```

这段配置表达的意思其实很简单：

👉 `Spring` 先创建对象。  
👉 然后一个个调用 `setter` 方法。  
👉 把属性值注入进去。

如果使用 `p-namespace`，写法可以变得更简洁。

```xml
<bean id="myDataSource"
      class="org.apache.commons.dbcp.BasicDataSource"
      destroy-method="close"
      p:driverClassName="com.mysql.jdbc.Driver"
      p:url="jdbc:mysql://localhost:3306/mydb"
      p:username="root"
      p:password="misterkaoli"/>
```

你能看到的区别只有一个，不再写 `<property>`，直接写成 `p:属性名="值"`，其他效果完全相同。

比如 `p:username="root"` 在内部实际上等价于 `<property name="username" value="root"/>`，它本质上只是语法糖，也就是对写法的简化。

如果你想通过 `p-namespace` 注入一个**对象类型的属性**（而不是基本类型或字符串），就必须使用 `p:spouse-ref`，而不能简单写成 `p:spouse`。

[p-namespace](../../../example/p-namespace.md)

# <ref>

`<ref>` 只能写在 `<property>...</property>` 或者 `<constructor-arg>...</constructor-arg>` 里面，不能单独存在。

它的作用不是给属性赋字符串，而是给属性注入**另一个对象**。换句话说，它不是把 `"userService"` 这个字符串塞进去，而是把对应的 `Bean` 实例塞进去。

来看个例子：

```xml
<bean id="userService" class="UserService"/>

<bean id="orderService" class="OrderService">
    <property name="userService">
        <ref bean="userService"/>
    </property>
</bean>
```

这段配置表达的意思是：

```plain
orderService.userService = userService对象
```

注意，是对象，不是字符串 `"userService"`。

如果 `A` 通过 `<ref>` 引用了 `B`，那么 `Spring` 会保证 **B 一定先准备好**，再把它注入到 `A` 里。

当 `Spring` 准备创建 `A` 的时候，如果发现 `A` 依赖 `B`，它会这样处理：

1. 先去创建 `B`（如果 `B` 还没创建）。
2. 再把 `B` 设置到 `A` 的对应属性里。

如果 `B` 是单例（`singleton`），可能在容器启动时就已经创建好了，`Spring` 会直接拿现成的实例使用。

[ref](../../../example/ref.md)

之前我们讲过，一个项目里是可以拆分成多个 `XML` 配置文件的。这些配置文件可以分别定义各自的 `Bean`，只要最后把它们**一起加载到同一个 Spring 容器中**，它们之间就可以互相引用。

也就是说：

+ 哪怕 `A Bean` 写在 `application-context-a.xml`。
+ 哪怕 `B Bean` 写在 `application-context-b.xml`。

只要这两个 `XML` 最终都被加载进同一个 `ApplicationContext`，那么：

+ `A` 可以 `<ref>` `B`。
+ `B` 也可以 `<ref>` `A`（不考虑循环依赖的情况下）。

关键点不在于“写在不在同一个 `XML` 文件”，而在于“是不是在同一个容器里”，容器才是边界，文件不是。

# 内部 Bean

你可以在 `<property>` 或 `<constructor-arg>` 里面直接嵌套一个临时的 `<bean>`，这种 `Bean` 不会作为全局 `Bean` 注册到容器中，只是专门为包裹它的那个 `Bean` 服务。

看这个例子：

```xml
<bean id="outer" class="...">
  <property name="target">
    <bean class="com.example.Person">
      <property name="name" value="Fiona Apple"/>
      <property name="age" value="25"/>
    </bean>
  </property>
</bean>
```

这里相当于容器里只有 `outer` 这个 `Bean`，在给 `target` 属性赋值时，我们直接在 `XML` 里创建了一个 `Person` 对象。这个 `Person` 不是通过 `<ref>` 引用的，而是当场创建并注入进去的。

内部 `bean` 不需要 `id` 或 `name`，因为它不会被注册到全局容器中。它只是一个临时创建的 `Bean`，只在定义它的那个位置使用。容器里不会保存它的名字，也无法通过 `<ref>` 去引用它。换句话说，这个临时 `Bean` 只在当前包裹它的 `<property>` 或 `<constructor-arg>` 里生效。离开这个位置，它就没有任何作用，其他地方既用不到，也无法访问。

> 注意 ⚠️：在使用这种内部 `Bean` 写法时，必须确保外部 `Bean` 和内部 `Bean` 的作用域保持一致。也就是说，如果外部 `Bean` 是单例（`singleton`），那内部 `Bean` 也应该是单例，如果外部是多例（`prototype`），内部也应该是多例。否则就可能出现作用域不匹配的问题。
>

[内部 Bean](../../../example/inner-bean.md)

# 集合

你也可以在 `XML` 里直接给 `Bean` 注入集合类型（`Collection`），只需要使用 `<list/>`、`<set/>`、`<map/>` 和 `<props/>`，这几个标签分别对应 `java.util.List`、`java.util.Set`、`java.util.Map`、`java.util.Properties`。

当 `Spring` 解析到这些元素时，它会：

1. 创建对应的集合对象。
2. 把你在 `XML` 里配置的元素放进去。
3. 最后通过 `setter` 方法注入到目标 `Bean` 中。

你负责在 `XML` 里声明数据结构，`Spring` 负责帮你创建集合对象并完成注入。

看个例子你就明白了，我们用 `<props>` 来演示。

```xml
<property name="adminEmails">
    <props>
        <prop key="administrator">administrator@example.org</prop>
        <prop key="support">support@example.org</prop>
    </props>
</property>
```

这段配置的意思是给 `adminEmails` 这个属性注入一个 `Properties` 对象。

等价的 `Java` 代码就是：

```java
Properties p = new Properties();
p.put("administrator", "administrator@example.org");
p.put("support", "support@example.org");

complexObject.setAdminEmails(p);
```

也就是说，`Spring` 会先帮你创建一个 `Properties` 对象，把 `<prop>` 里的 `key` 和 `value` 放进去，然后再通过 `setter` 方法注入。

> 在 `<map>` 里，不管是 `key` 还是 `value`，都可以写成各种合法的子元素；同样，在 `<list>`、`<set>` 这类集合里，`value` 也可以定义成不同类型的元素。
>

## 强类型集合

得益于 `Java` 的泛型支持，你可以使用强类型集合。也就是说，在定义 `Bean` 属性的时候，可以直接用泛型指定集合接受的类型。

例如：

```java
public class SomeClass {
    private Map<String, Float> accounts;

    public void setAccounts(Map<String, Float> accounts) {
        this.accounts = accounts;
    }
}
```

然后在 `XML` 里可以这样写：

```xml
<beans>
    <bean id="something" class="x.y.SomeClass">
        <property name="accounts">
            <map>
                <entry key="one" value="9.99"/>
                <entry key="two" value="2.75"/>
                <entry key="six" value="3.99"/>
            </map>
        </property>
    </bean>
</beans>
```

`Spring` 在注入时会通过反射获取 `accounts` 属性的类型信息，然后自动进行类型转换，把 `<map>` 里的字符串值转换成对应的 `Float` 对象，再注入到 `Bean` 中。

[集合注入。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/diwyvzo32fit4d2a)

# 空值与空字符串值

在 `Spring Framework` 的 `XML` 配置里，`""`（空字符串）和 `null` 是两种完全不同的值。它们表面上看起来都“什么都没有”，但在 `Java` 中语义完全不同。

先看空字符串 `""` 的赋值代码。

```xml
<bean class="ExampleBean">
    <property name="email" value=""/>
</bean>
```

`Spring` 会理解成 `exampleBean.setEmail("");`，也就是说，`email` 不是 `null`，而是一个长度为 `0` 的字符串，这是一个真实存在的 `String` 对象。

再看 `<null/>` 的赋值代码。

```xml
<bean class="ExampleBean">
    <property name="email">
        <null/>
    </property>
</bean>
```

`Spring` 会理解成 `exampleBean.setEmail(null);`，也就是说，`email` 根本没有对象，是 `Java` 的 `null`，访问 `email.length()` 会直接抛出 `NullPointerException`。

[空字符串和 null 的注入。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/sdyz8idsv60vetup)

# c-namespace

`c-namespace` 本质上就是 **构造器注入版本的 p-namespace**，专门用来简化构造函数参数的注入写法。

假设类定义如下：

```java
public class ThingOne {

    public ThingOne(ThingTwo thingTwo,
                    ThingThree thingThree,
                    String email) {
    }
}
```

如果使用传统的 `XML` 构造器注入写法，需要嵌套多个 `<constructor-arg>`：

```xml
<bean id="beanOne" class="x.y.ThingOne">
    <constructor-arg name="thingTwo" ref="beanTwo"/>
    <constructor-arg name="thingThree" ref="beanThree"/>
    <constructor-arg name="email" value="something@somewhere.com"/>
</bean>
```

写法比较规整，但结构显得有些冗长。

使用 `Spring Framework` 提供的 `c-namespace`，可以直接压缩为属性形式，写得更加紧凑。

```xml
<bean id="beanOne"
      class="x.y.ThingOne"
      c:thingTwo-ref="beanTwo"
      c:thingThree-ref="beanThree"
      c:email="something@somewhere.com"/>
```

可以看到：

+ `c:参数名-ref` 用于注入 **对象引用。**
+ `c:参数名` 用于注入 **基本类型或字符串等字面值。**

因此，`c-namespace` 同时支持注入基本数据类型和引用类型，只是写法更简洁而已。

[c-namespace。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/il0mm03goxgwb4ho)

# 复合属性名

在 `Spring Framework` 的 `XML` 配置里，是支持“链式设置属性”的，效果有点像 `Java` 里的连续调用，对象点对象再点对象。

比如你可以这样写：

```xml
<property name="fred.bob.sammy" value="123" />
```

它等价于下面这段 `Java` 调用逻辑：

```java
something.getFred().getBob().setSammy("123");
```

来看一个完整例子。假设有如下类结构：

```java
class ThingOne {
    private Fred fred;
}

class Fred {
    private Bob bob;
}

class Bob {
    private String sammy;
}
```

然后在 `XML` 中这样配置：

```xml
<bean id="something" class="things.ThingOne">
    <property name="fred.bob.sammy" value="123" />
</bean>
```

`Spring` 在处理时，会按照属性路径逐级查找：

1. 先获取 `something` 的 `fred`。
2. 再获取 `fred` 里的 `bob`。
3. 最后调用 `bob.setSammy("123")`。

也就是说，`fred.bob.sammy` 这种点分路径，本质上就是多层 `getter` 之后，最终调用最内层对象的 `setter` 方法。

> 需要特别注意的是，在这种链式属性注入中，除了最后一个属性以外，前面的对象都不能是 `null`。也就是说，`fred` 不能为 `null`，`bob` 也不能为 `null`，因为 `Spring` 在处理 `fred.bob.sammy` 时，本质上是在依次调用 `something.getFred().getBob().setSammy("123")`。它不会帮你自动创建中间对象，如果某一层返回的是 `null`，继续调用下去就会直接抛出 `NullPointerException`。
>

[复合属性。](https://www.yuque.com/diqiyexu-vgtwd/kgih55/wywo1anhog2411nr)