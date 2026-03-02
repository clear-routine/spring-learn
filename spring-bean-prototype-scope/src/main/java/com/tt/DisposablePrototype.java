package com.tt;

/**
 * 原型 Bean 的自定义销毁接口。
 * 由于 Spring 不会为原型作用域的 Bean 调用 @PreDestroy，
 * 客户端需要负责资源释放。此接口用于配合 PrototypeBeanCleanupProcessor，
 * 在容器关闭时统一清理已创建的原型 Bean。
 */
public interface DisposablePrototype {

    /**
     * 执行自定义销毁逻辑，如关闭连接、释放资源等
     */
    void customDestroy();
}
