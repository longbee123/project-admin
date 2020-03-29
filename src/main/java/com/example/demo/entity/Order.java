package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

//    @Column(name = "status" )
//    private int status;

    @Column(name = "username")
    private String username;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "total_money")
    private float totalMoney;
    @Column(name = "phone_number")
    private String phoneNumber;

    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> listProductOrders = new ArrayList<>();


}
