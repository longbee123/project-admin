package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int productId;
    private String name;
    private String thumbnail;
    private String description;
    private Date createDate;
    private int stockAmount;
    private float price;
}
