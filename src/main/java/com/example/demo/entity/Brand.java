package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="brand")
@Entity
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    @Column(name="brand")
    private String name;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
