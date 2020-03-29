package com.example.demo.controller;


import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/order")
@RestController
public class OrderController{

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOderItemById(@PathVariable("id") int id,Model model){
            model.addAttribute("order",orderService.orderItem(id));
        return ResponseEntity.ok("success");
    }


}