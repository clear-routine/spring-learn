package com.tt;

import com.tt.order.Order;

/**
 * 实例工厂类
 * 需先实例化为 Bean，再调用其实例方法创建目标 Bean
 */
public class OrderFactory {

    /** 无参实例工厂方法 */
    public Order createInstance() {

        return new Order("default");
    }

    /** 带参实例工厂方法 */
    public Order createInstance(String orderId) {

        return new Order(orderId);
    }
}
