package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.entity.Product;


import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderTemplateController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/admin/order")
    public String oderList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @ModelAttribute("name") String name){
        model.addAttribute("listorder",orderService.search(name,page,size));
        model.addAttribute("name",name);

        return "listorder";
    }


}
