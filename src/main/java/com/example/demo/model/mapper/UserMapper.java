package com.example.demo.model.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;

import com.example.demo.model.request.RegisterForm;
import com.example.demo.model.request.UpdateForm;
import org.mindrot.jbcrypt.BCrypt;



public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setPhone(user.getPhone());
        tmp.setEmail(user.getEmail());

        return tmp;
    }
    public static User toUser(RegisterForm form){
        User tmp = new User();
        tmp.setName(form.getName());
        tmp.setEmail(form.getEmail());
        tmp.setPhone(form.getPhone());
        String hash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));
        tmp.setPassword(hash);
        tmp.setRole("USER");


        return tmp;
    }
    public static User toUser(UpdateForm form, int id){
        User tmp = new User();
        tmp.setId(id);
        tmp.setName(form.getName());
        tmp.setPhone(form.getPhone());
        String hash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));
        tmp.setPassword(hash);
        tmp.setRole("USER");


        return tmp;
    }

}
