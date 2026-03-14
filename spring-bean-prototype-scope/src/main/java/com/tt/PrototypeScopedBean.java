package com.tt;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;

import java.util.UUID;

/**
 * 原型作用域的 Bean，用于演示：
 * 1. 前置钩子（@PostConstruct）：每次创建实例时会被 Spring 调用
 * 2. 后置钩子（@PreDestroy）：Spring 不会为原型 Bean 调用，需要自定义处理
 */
public class PrototypeScopedBean implements DisposablePrototype {

    @Getter
    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);
    private boolean resourceAcquired = false;

    /**
     * 前置钩子：每次 Bean 创建并配置完成后，Spring 会调用此方法
     */
    @PostConstruct
    public void init() {

        // 模拟获取资源（如数据库连接、文件句柄等）
        resourceAcquired = true;
        System.out.println("  [前置钩子 @PostConstruct] 实例 " + instanceId + " 初始化完成，资源已获取");
    }

    /**
     * 后置钩子：Spring 不会为原型 Bean 自动调用此方法！
     * 原型 Bean 交给客户端后，Spring 不再管理其生命周期。
     */
    @PreDestroy
    public void destroy() {

        System.out.println("  [@PreDestroy] 实例 " + instanceId + " 的 destroy 被调用（原型 Bean 下 Spring 不会自动调用）");
        if (resourceAcquired) {
            // 模拟释放资源
            resourceAcquired = false;
            System.out.println("  >>> 实例 " + instanceId + " 已释放资源");
        }
    }

    /**
     * 自定义销毁逻辑：由 PrototypeBeanCleanupProcessor 在适当时机调用
     * 实现 DisposablePrototype 接口，供后处理器统一清理
     */
    @Override
    public void customDestroy() {

        System.out.println("  [自定义后置钩子] 实例 " + instanceId + " 执行自定义清理");
        if (resourceAcquired) {
            // 模拟释放资源
            resourceAcquired = false;
            System.out.println("  >>> 实例 " + instanceId + " 已释放资源");
        }
    }
}
