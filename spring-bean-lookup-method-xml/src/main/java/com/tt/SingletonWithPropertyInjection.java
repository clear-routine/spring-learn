package com.tt;

/**
 * 错误示范：单例通过属性注入 prototype
 *
 * 属性注入只在创建时执行一次，所以 singleton 永远持有同一个 prototype 实例，
 * 无法实现"每次调用都拿新实例"的效果。
 */
public class SingletonWithPropertyInjection {

    private PrototypeBean prototypeBean;

    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }

    public void doSomething() {
        prototypeBean.doWork();  // 每次都是同一个实例
    }
}
