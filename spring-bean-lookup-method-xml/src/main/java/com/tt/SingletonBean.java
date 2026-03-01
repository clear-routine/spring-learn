package com.tt;

/**
 * 单例 Bean，需要每次调用时都拿到新的 PrototypeBean
 *
 * 通过 lookup-method（XML），Spring 用 CGLIB 生成子类，重写 getPrototypeBean() 方法。
 * 每次调用时，子类实现会去容器里重新 getBean，从而拿到新的 prototype 实例。
 */
public abstract class SingletonBean {

    /**
     * 抽象方法：Spring 通过 CGLIB 生成的子类会重写此方法，
     * 实现为每次调用时从容器获取新的 prototypeBean
     */
    public abstract PrototypeBean getPrototypeBean();

    /**
     * 业务方法：每次调用都会通过 getPrototypeBean() 拿到新实例
     */
    public void doSomething() {
        PrototypeBean bean = getPrototypeBean();
        bean.doWork();
    }
}
