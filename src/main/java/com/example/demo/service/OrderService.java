package com.example.demo.service;


import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Page<Order> search(String name, int page, int size);
    public List<OrderItem> orderItem(int id);
}
