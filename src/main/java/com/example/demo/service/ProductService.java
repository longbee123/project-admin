package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Page<Product> search(String name , int page,int size);
    public ProductDto creatProduct(Product product, int id);
    public void deleteProduct(int id);
    public Product getProductById(int id);
    public ProductDto updateProduct(Product product , int id);

}
