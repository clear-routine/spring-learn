package com.tt.user;

/**
 * User2 类 - 用于 @Bean 方法返回（显式配置）
 * 这个类本身不使用 @Component 注解
 * 通过 @Bean 方法显式注册为 Bean
 */
public class User2 {
    
    private String name = "第二个User";
    
    public User2(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void process() {
        System.out.println("User2[" + name + "]: 处理用户请求（来自 @Bean）");
    }
}
