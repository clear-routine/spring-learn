package com.tt.controller;

import com.tt.service.OrderService;
import lombok.Setter;

public class OrderController {

    @Setter
    private OrderService orderService;

    public void handle() {

        System.out.println("OrderController: 处理订单请求");
        orderService.createOrder("ORDER-002");
    }
}
