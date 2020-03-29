package com.example.demo.controller;


import com.example.demo.entity.Brand;

import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.BrandForm;
import com.example.demo.model.request.RegisterForm;
import com.example.demo.model.request.UpdateForm;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;

@RequestMapping("/category")
@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandRepository brandRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand){
            Brand result = brandService.createBrand(brand);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@RequestBody BrandForm brand , @PathVariable("id") int id) throws ParseException {
        Brand brand1 = brandService.updateBrand(brand , id);
        return ResponseEntity.status(HttpStatus.OK).body(brand1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id){
        brandService.deleteBrand(id);

        return ResponseEntity.status(HttpStatus.OK).body("Delete user successfully");
    }

}
