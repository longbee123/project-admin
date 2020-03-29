package com.example.demo.entity;



import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'USER'")
//    private String role;
    @NotEmpty
    @Column(name="name")
    private String  name;
    @Email
    @Column(name="email",unique = true)
    private String email;

    @Column(name="phone", length = 11)
    private String phone;

    @Column(name="password")
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'USER'")
    private String role;

}
