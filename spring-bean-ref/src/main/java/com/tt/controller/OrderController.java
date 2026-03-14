package com.tt.controller;

import com.tt.service.OrderService;
import lombok.Getter;
import lombok.Setter;

/**
 * 依赖 OrderService，用于演示 property + ref（Setter 注入）
 */
@Getter
public class OrderController {

    @Setter
    private OrderService orderService;

    public void handle() {

        System.out.println("OrderController: 处理请求");
        orderService.createOrder("ORDER-001");
    }
}
