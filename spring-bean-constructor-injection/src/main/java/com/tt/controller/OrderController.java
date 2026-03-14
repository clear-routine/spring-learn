package com.tt.controller;

import com.tt.service.OrderService;

/**
 * 构造器注入
 */
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    public void handle() {

        System.out.println("OrderController: 处理订单请求");
        orderService.createOrder("ORDER-001");
    }
}
