package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * depends-on 演示：控制 Bean 初始化顺序
 * 
 * 场景说明：
 * - DataSourceUser 依赖于 DriverRegister 的初始化操作
 * - 但两者之间没有直接的属性注入关系（没有 ref，没有 @Autowired）
 * - 使用 depends-on 确保 DriverRegister 先于 DataSourceUser 初始化
 * 
 * 运行此程序，观察控制台输出，可以看到：
 * 1. DriverRegister 先被创建和初始化
 * 2. 然后 DataSourceUser 才被创建
 * 3. DataSourceUser 创建时，驱动已经注册完成
 */
public class Main {

    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        
        System.out.println();
        System.out.println("========== 容器加载完成 ==========");
        System.out.println();
        
        // 获取 Bean
        DataSourceUser dataSourceUser = context.getBean("dataSourceUser", DataSourceUser.class);
        DriverRegister driverRegister = context.getBean("driverRegister", DriverRegister.class);
        
        System.out.println("========== 使用 Bean ==========");
        System.out.println();
        dataSourceUser.useDataSource();
    }
}
