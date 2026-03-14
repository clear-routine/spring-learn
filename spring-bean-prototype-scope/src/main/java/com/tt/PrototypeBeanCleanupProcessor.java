package com.tt;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 Bean 后处理器，用于管理原型 Bean 的销毁（自定义后置钩子）。
 *
 * 1. BeanPostProcessor：在原型 Bean 创建后，将实现 DisposablePrototype 的 Bean 注册到集合中
 * 2. DisposableBean：本 Processor 为单例，容器关闭时 Spring 会调用 destroy()，
 *    此时遍历所有已创建的原型 Bean，调用其 customDestroy() 执行清理
 *
 * Spring 不会为原型 Bean 调用 @PreDestroy，此处理器提供了"自定义后置销毁钩子"的解决方案。
 */
public class PrototypeBeanCleanupProcessor implements BeanPostProcessor, DisposableBean {

    private final List<DisposablePrototype> prototypeBeans = new ArrayList<>();

    /**
     * BeanPostProcessor 接口方法。容器中每一个 bean 初始化完成后都会调用此方法（单例、原型均会调用）。
     * 作用：将实现 DisposablePrototype 的 bean 登记到 prototypeBeans 集合中，以便容器关闭时统一清理。
     */
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        System.out.println("  [postProcessAfterInitialization] 被调用，beanName: " + beanName);
        if (bean instanceof DisposablePrototype) {
            prototypeBeans.add((DisposablePrototype) bean);
        }
        return bean;
    }

    /**
     * DisposableBean 接口方法。此方法只会在当前类（PrototypeBeanCleanupProcessor）的 bean 被销毁时被调用一次。
     * 作用：遍历已登记的所有 bean，依次调用其 customDestroy()，执行资源释放逻辑。
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("\n[PrototypeBeanCleanupProcessor] 容器关闭，destroy() 被调用，" +
                "清理 " + prototypeBeans.size() + " 个原型 Bean");
        for (DisposablePrototype prototype : prototypeBeans) {
            prototype.customDestroy();
        }
    }
}
