package com.tt.service;

import com.tt.repository.OrderRepository;

/**
 * OrderService 依赖 OrderRepository
 * 支持 setter 注入（byName、byType）和构造器注入（constructor）
 */
public class OrderService {

    private OrderRepository orderRepository;

    /** 无参构造，用于 setter 注入 */
    public OrderService() {
    }

    /** 有参构造，用于 constructor 自动装配 */
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(String orderId) {
        System.out.println("OrderService: 创建订单 " + orderId);
        orderRepository.save(orderId);
    }
}
