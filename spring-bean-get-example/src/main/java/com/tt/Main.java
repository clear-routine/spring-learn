package com.tt;

import com.tt.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean 获取方式示例
 *
 * 演示三种从 Spring 容器获取 Bean 的方式
 */
public class Main {

    public static void main(String[] args) {

        // 加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println("========== 方式一：通过 name + class 获取 ==========");
        // 通过 name "userController" 获取
        // 这里的 "userController" 是你在 XML 配置文件中定义的 Bean 的 id 属性，
        // 而 class 则表示该 Bean 对应的具体类型。
        UserController controller1 = context.getBean("userController", UserController.class);
        controller1.handle();
        System.out.println();

        System.out.println("========== 方式二：通过 class 获取 ==========");
        // 通过类型获取（如果只有一个 UserController 类型的 Bean）
        // 如果项目中只有一个 UserController 类型的 Bean，就可以按上面的方法直接获取。
        // 但如果存在多个同类型的 Bean，那么这种方式就无法获取到具体的实例了。
        UserController controller2 = context.getBean(UserController.class);
        controller2.handle();
        System.out.println();

        System.out.println("========== 方式三：通过 name 获取 ==========");
        // 只通过 name，返回 Object，需要强制转换
        // 这种方式因为没有指定 class 类型，所以获取到的对象是 Object 类型，需要手动进行类型转换。
        Object obj = context.getBean("userController");
        UserController controller3 = (UserController) obj;
        controller3.handle();
        System.out.println();

        System.out.println("========== 验证：三个对象是否为同一个实例（单例模式） ==========");
        System.out.println("controller1 == controller2: " + (controller1 == controller2));
        System.out.println("controller2 == controller3: " + (controller2 == controller3));
        System.out.println("controller1 == controller3: " + (controller1 == controller3));
    }
}
