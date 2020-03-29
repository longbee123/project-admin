package com.example.demo.model.request;

import com.example.demo.entity.Brand;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ProductRegisterForm {

    private String name;
    private String thumbnail;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    private int stockAmount;
    private float price;
    private String brandId;
}
