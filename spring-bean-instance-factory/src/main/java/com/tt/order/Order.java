package com.tt.order;

/**
 * 订单 - 由实例工厂方法创建
 */
public class Order {

    private final String orderId;

    public Order(String orderId) {

        this.orderId = orderId;
    }

    public void handle() {

        System.out.println("Order 处理订单 [" + orderId + "]");
    }
}
