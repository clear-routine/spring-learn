package com.tt;

/**
 * 数据源用户类
 * 
 * 这个类依赖于 DriverRegister 的初始化操作，
 * 但不通过属性注入的方式引用 DriverRegister 实例。
 * 
 * 在实际场景中，可能是在初始化方法中需要使用已注册的数据库驱动。
 */
public class DataSourceUser {
    
    public DataSourceUser() {
        System.out.println("[DataSourceUser] 构造函数被调用");
        
        // 检查驱动是否已注册
        if (DriverRegister.isDriverRegistered()) {
            System.out.println("[DataSourceUser] ✓ 数据库驱动已就绪，可以安全使用");
        } else {
            System.out.println("[DataSourceUser] ✗ 警告：数据库驱动尚未注册！这可能导致运行时错误");
        }
        
        // 模拟使用数据库连接的操作
        initializeDataSource();
    }
    
    /**
     * 初始化数据源
     * 这个方法依赖于 DriverRegister 的初始化操作
     */
    private void initializeDataSource() {
        System.out.println("[DataSourceUser] 正在初始化数据源...");
        // 这里会使用已注册的数据库驱动
        System.out.println("[DataSourceUser] ✓ 数据源初始化完成");
    }
    
    /**
     * 使用数据源
     */
    public void useDataSource() {
        System.out.println("[DataSourceUser] 使用数据源执行操作");
    }
}
