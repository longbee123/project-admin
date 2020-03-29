package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Page<Product> search(String name, int page , int size) {
        Page<Product> products = productRepository.findAllByNameContaining(name, PageRequest.of(page,size, Sort.by("createDate").descending()));

        return products;
    }

    @Override
    public ProductDto creatProduct(Product product , int id) {
        Product product1 = productRepository.findByName(product.getName().trim());
        Optional<Brand> brand = brandRepository.findById(id);
        if(product1 != null){
            throw new DuplicateRecordException("Tên sản phẩm đã tồn tại");
        }else {
            Product product2 = new Product();
            product2.setName(product.getName());
            product2.setCreateDate(new Date());
            product2.setPrice(product.getPrice());
            product2.setDescription(product.getDescription());
            product2.setThumbnail(product.getThumbnail());
            if(brand == null){
                throw  new NotFoundException("Không tìm thấy hãng xe");
            }else {
                product2.setBrand(brand.get());
            }
            productRepository.save(product2);
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setCreateDate(product.getCreateDate());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            productDto.setThumbnail(product.getThumbnail());
            return productDto;
        }

    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new NotFoundException("Không tìm thấy sản phẩm");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw  new NotFoundException("Không tìm thấy sản phẩm");
        }
        Product product1 = new Product();
        product1.setName(product.get().getName());
        product1.setBrand(product.get().getBrand());
        product1.setPrice(product.get().getPrice());
        product1.setCreateDate(product.get().getCreateDate());
        product1.setDescription(product.get().getDescription());

        return product1;
    }

    @Override
    public ProductDto updateProduct(Product product, int id) {
        Optional<Product> productOptional = productRepository.findById(id);
       Product product1 = new Product();
        product1.setProductId(id);
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setCreateDate(productOptional.get().getCreateDate());
        product1.setBrand(productOptional.get().getBrand());
        product1.setThumbnail(product.getThumbnail());
        productRepository.save(product1);
       ProductDto productDto = new ProductDto();
       productDto.setName(product1.getName());
       productDto.setDescription(product1.getDescription());
       productDto.setPrice(product1.getPrice());
       productDto.setProductId(product1.getProductId());
        return productDto;
    }


}
