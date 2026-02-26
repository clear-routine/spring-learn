package com.tt.repository;

public class OrderRepository {

    public void save(String orderId) {
        System.out.println("OrderRepository: 保存订单 " + orderId);
    }
}
