package com.tt.service;

import com.tt.repository.OrderRepository;
import lombok.Setter;

public class OrderService {

    @Setter
    private OrderRepository orderRepository;

    public void createOrder(String orderId) {

        System.out.println("OrderService: 创建订单 " + orderId);
        orderRepository.save(orderId);
    }
}
