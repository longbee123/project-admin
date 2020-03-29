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
public class UpdateForm {

    @NotEmpty(message = "Vui lòng nhập tên")
    private String name;
    @Size(min =4 , max = 20,message = "password tối thiếu từ 4 kí tự và không vượt quá 20 kí tự" )
    private String password;
    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b",message = "password tối thiếu từ 4 kí tự và không vượt quá 20 kí tự")
    private String phone;
}
