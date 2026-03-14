package com.tt.service;

import com.tt.repository.OrderRepository;

/**
 * 构造器注入：依赖通过构造方法一次性注入
 */
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public void createOrder(String orderId) {

        System.out.println("OrderService: 创建订单 " + orderId);
        orderRepository.save(orderId);
    }
}
