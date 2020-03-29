package com.example.demo.service;


import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    public List<OrderItem> listOrderItem(int id);
    public Order getOrderByOrderItem(int id);
}
