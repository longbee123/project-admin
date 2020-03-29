package com.example.demo.controller;


import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.UpdateForm;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create/{id}")
    public ResponseEntity<?> creatProduct(@RequestBody Product product , @PathVariable("id") int id){
        ProductDto result = productService.creatProduct(product , id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){
        List<Product> result = productRepository.findAll();
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body("Delete user successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser( @RequestBody Product product , @PathVariable("id") int id
                                                ){
        ProductDto result = productService.updateProduct(product,id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
