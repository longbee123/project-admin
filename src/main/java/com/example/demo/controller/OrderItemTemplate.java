package com.example.demo.controller;


import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderItemTemplate {

    @Autowired
    private OrderItemService orderItemService;
    @GetMapping("/admin/order/{id}")
    public String orderItem(@PathVariable("id") int id, Model model){
        model.addAttribute("order" , orderItemService.listOrderItem(id));
        model.addAttribute("ordertt",orderItemService.getOrderByOrderItem(id));
        return "listorderitem";
    }
}
