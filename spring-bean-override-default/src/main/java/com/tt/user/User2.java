package com.tt.user;

/**
 * 第二个 User 类
 */
public class User2 {
    
    private String name = "第二个User";
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void process() {
        System.out.println("User2[" + name + "]: 处理用户请求");
    }
}
