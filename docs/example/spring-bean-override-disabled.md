# 禁止 Spring 的 Bean 覆盖行为

详细的代码可以参考 `spring-bean-override-disabled` 模块。

# Main.java

```java
public class Main {

    public static void main(String[] args) {

        try {
            // 使用 GenericXmlApplicationContext 可以设置 Bean 覆盖策略
            // 通过 setAllowBeanDefinitionOverriding(false) 禁止 Bean 覆盖
            // 如果出现同名 Bean，容器启动直接失败并抛异常
            GenericXmlApplicationContext context = new GenericXmlApplicationContext();
            context.setAllowBeanDefinitionOverriding(false);  // 禁止 Bean 覆盖
            context.load("user1-config.xml", "user2-config.xml");
            context.refresh();  // 这里会抛出异常
            
            // 如果执行到这里，说明没有同名 Bean
            Object userBean = context.getBean("user");
            System.out.println("获取到的 Bean 类型: " + userBean.getClass().getSimpleName());
            
            if (userBean instanceof User1) {
                User1 user = (User1) userBean;
                System.out.println("最终获取到的 Bean: " + user.getName());
                user.process();
            } else if (userBean instanceof User2) {
                User2 user = (User2) userBean;
                System.out.println("最终获取到的 Bean: " + user.getName());
                user.process();
            }
        } catch (Exception e) {
            System.out.println("✓ 正确！禁止覆盖后，出现同名 Bean 会直接报错：");
            System.out.println("  异常类型: " + e.getClass().getSimpleName());
            System.out.println("  异常信息: " + e.getMessage());
        }
    }
}
```

**为什么必须在 **`ApplicationContext`** 刷新之前设置这个标志？**原因在于 **Spring **`ApplicationContext`** 的整体加载流程**。大致可以分为下面几个步骤：

1. **创建容器：**`new GenericXmlApplicationContext()`，此时只是一个**空容器**，还没有任何 `Bean`。
2. **设置属性：**`setAllowBeanDefinitionOverriding(false)`，这一步**必须发生在 **`refresh()`** 之前**，用于提前声明覆盖策略。
3. **加载配置：**`load(...)`，这里只是标记哪些配置文件需要加载，**并不会立刻解析**。
4. **刷新容器（**`refresh()`**）：**这是 `Spring` 容器加载的真正核心阶段，在这个阶段，`Spring` 会实际执行解析 `XML` 配置、注册 `Bean` 定义、按照之前设置的策略检查是否允许 `Bean` 覆盖，以及创建和初始化 `Bean` 的操作。

`refresh()`** 才是真正执行加载和校验的地方**。在 `refresh()` 过程中，`Spring` 会读取 `allowBeanDefinitionOverriding` 这个标志，如果它被设置为 `false`，并且检测到同名 `Bean`，就会**立即抛出异常并终止启动**。

如果你在 `refresh()`** 之后** 才去调用 `setAllowBeanDefinitionOverriding(false)`，此时 `Bean` 已经加载完成、覆盖检查也已经执行过了，再修改这个标志已经**不会产生任何效果**。**因此，必须在 **`refresh()`** 之前设置该标志位，这是硬性前提。**