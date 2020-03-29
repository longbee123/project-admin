package com.example.demo.service;


import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> listOrderItem(int id) {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItem> orderItems1 = new ArrayList<>();
        for(OrderItem orderItem : orderItems){
            if(orderItem.getOrder().getOrderId()==id){
                orderItems1.add(orderItem);
            }

        }



        return orderItems1;
    }

    @Override
    public Order getOrderByOrderItem(int id) {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        Order order = new Order();
        for(OrderItem orderItem : orderItems){
            if(orderItem.getOrder().getOrderId()==id){
                order.setOrderId(orderItem.getOrderItemId());
                order.setCustomerName(orderItem.getOrder().getCustomerName());
                order.setShipAddress(orderItem.getOrder().getShipAddress());
                order.setTotalMoney(orderItem.getOrder().getTotalMoney());
                order.setPhoneNumber(orderItem.getOrder().getPhoneNumber());
            }

        }
        return order;
    }

}
