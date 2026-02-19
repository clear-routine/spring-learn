# 使用容器

详细代码可以直接参考 `spring-bean-get-example` 模块即可。

# 通过 name + class 获取

```java
// 通过 name "userController" 获取
UserController controller = context.getBean("userController", UserController.class);
```

这里的 `"userController"` 是你在 `XML` 配置文件中定义的 `Bean` 的 `id` 属性，而 `class` 则表示该 `Bean` 对应的具体类型。

# 通过 class 获取

```java
// 通过类型获取（如果只有一个 UserController 类型的 Bean）
UserController controller = context.getBean(UserController.class);
```

如果项目中只有一个 `UserController` 类型的 `Bean`，就可以按上面的方法直接获取。但如果存在多个同类型的 `Bean`，那么这种方式就无法获取到具体的实例了。

# 通过 name 获取

```java
// 只通过 name，返回 Object，需要强制转换
Object obj = context.getBean("userController");
UserController controller = (UserController) obj;
```

这种方式因为没有指定 `class` 类型，所以获取到的对象是 `Object` 类型，需要手动进行类型转换。