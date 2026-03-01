package com.tt;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * 使用 @Lookup 注解实现方法注入
 *
 * 与 XML 的 lookup-method 底层原理相同：Spring 用 CGLIB 生成子类，重写 getPrototypeBean() 方法。
 */
@Component
public class SingletonWithLookup {

    /**
     * @Lookup：Spring 在运行时重写此方法，每次调用时从容器获取新的 prototypeBean
     */
    @Lookup
    public PrototypeBean getPrototypeBean() {
        return null;  // 运行时会被 CGLIB 生成的实现替换，不会执行到这里
    }

    public void doSomething() {
        PrototypeBean bean = getPrototypeBean();
        bean.doWork();
    }
}
