package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.entity.User;
import com.example.demo.model.dto.BrandDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.BrandForm;
import com.example.demo.model.request.RegisterForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface BrandService {
    public Page<BrandDto> search(String name,  int page, int size) ;
    public BrandDto getBrandById(int id);
    public Brand updateBrand(BrandForm brand , int id) throws ParseException;
    public void deleteBrand(int id);
    public Brand createBrand(Brand brand);
    public List<Brand> getAllBrand();
}
