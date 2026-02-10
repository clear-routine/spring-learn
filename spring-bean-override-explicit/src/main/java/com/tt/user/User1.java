package com.tt.user;

/**
 * 第一个 User 类
 */
public class User1 {
    
    private String name = "第一个User";
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void process() {
        System.out.println("User1[" + name + "]: 处理用户请求");
    }
}
