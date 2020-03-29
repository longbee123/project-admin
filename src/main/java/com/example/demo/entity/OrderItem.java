package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderitem")
public class OrderItem  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private int orderItemId;

//    @Column(name = "amount")
//    private int amount;

//    @Column(name = "price")
//    private float price;
    @Column(name="quantity")
    private int quantity;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = true)
    @JoinColumn(name = "product_id")
    private Product product;
}
