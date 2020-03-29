package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private int id;
        @NotEmpty(message = "Vui lòng nhập tên")
        private String name;
        @NotEmpty(message = "Vui lòng nhập email")
        @Email(message = "email không đúng")
        private String email;
        @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b" , message = "phone number không đúng")
        private String phone;
        private String role;




}
