package com.tt;

/**
 * 数据库驱动注册类
 * 
 * 这个类在初始化时需要执行一些操作（比如注册数据库驱动），
 * 其他类可能依赖于这个初始化操作，但不直接引用这个类的实例。
 */
public class DriverRegister {
    
    private static boolean driverRegistered = false;
    
    public DriverRegister() {
        System.out.println("[DriverRegister] 构造函数被调用 - 开始注册数据库驱动");
        registerDriver();
        System.out.println("[DriverRegister] 数据库驱动注册完成");
    }
    
    /**
     * 注册数据库驱动
     * 这是一个初始化操作，需要在 DataSourceUser 使用之前完成
     */
    private void registerDriver() {
        if (!driverRegistered) {
            // 模拟注册数据库驱动的操作
            try {
                Thread.sleep(100); // 模拟耗时操作
                driverRegistered = true;
                System.out.println("[DriverRegister] ✓ 数据库驱动已成功注册");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            System.out.println("[DriverRegister] 数据库驱动已经注册过了");
        }
    }
    
    /**
     * 检查驱动是否已注册
     */
    public static boolean isDriverRegistered() {
        return driverRegistered;
    }
}
