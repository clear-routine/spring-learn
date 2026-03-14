package com.tt.service;

import com.tt.repository.OrderRepository;

/**
 * 依赖 OrderRepository，用于演示 constructor-arg + ref
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
