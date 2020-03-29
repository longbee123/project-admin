package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
   @NotEmpty(message = "Vui lòng nhập tên")
    private String name;
   @NotEmpty(message = "Vui lòng nhập email")
   @Email(message = "email không đúng")
    private String email;
   @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b" , message = "phone number không đúng")
    private String phone;
  @Size(min =4 , max = 20 , message = "password tối thiếu từ 4 kí tự và không vượt quá 20 kí tự")
    private String password;
}
