package com.example.demo.service;




import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.User;

import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Page<Order> search(String name, int page, int size) {
        Page<Order> orders = orderRepository.findAllByCustomerNameContaining(name,PageRequest.of(page,size));
        return orders;
    }

    @Override
    public List<OrderItem> orderItem(int id) {
        Optional<Order> order = orderRepository.findById(id);


        return order.get().getListProductOrders();
    }

}
