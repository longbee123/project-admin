package com.example.demo.entity;


import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column(name="name")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

//    @Type(type = "json")
//    @Column(name = "engine", columnDefinition = "json")
//    private Engine engine;

    @Column(name = "description",columnDefinition="TEXT")
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "stock_amount")
    private int stockAmount;

    @Column(name = "price")
    private float price;

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @ToString
//    public static class Engine implements Serializable {
//        private int cc;
//
//        private String type;
//    }

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<Images> images;


}
