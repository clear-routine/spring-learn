package com.tt.controller;

import com.tt.service.OrderService;

/**
 * OrderController 依赖 OrderService
 * 支持 setter 注入（byName、byType）和构造器注入（constructor）
 */
public class OrderController {

    private OrderService orderService;

    public OrderController() {
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void handle(String orderId) {
        System.out.println("OrderController: 处理请求 " + orderId);
        orderService.createOrder(orderId);
    }
}
