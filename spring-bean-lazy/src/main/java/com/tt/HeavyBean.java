package com.tt;

/**
 * 模拟重量级 Bean，构造函数中打印创建时机
 * 可选依赖：通过 setter 注入另一个 HeavyBean，用于演示依赖迫使延迟 Bean 提前创建
 */
public class HeavyBean {

    private final String name;
    private HeavyBean dependency;

    public HeavyBean(String name) {
        this.name = name;
        System.out.println(">>> [" + name + "] 构造函数被调用，Bean 被创建");
    }

    public void setDependency(HeavyBean dependency) {
        this.dependency = dependency;
    }

    public String getName() {
        return name;
    }
}
