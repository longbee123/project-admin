package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;

import com.example.demo.model.request.RegisterForm;
import com.example.demo.model.request.UpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public List<UserDto> getListUser();
    public UserDto getUserById(int id);
    public UserDto createUser(RegisterForm form);
    public UserDto updateUser(UpdateForm form, int id);
    public void updateUser(UserDto userDto);
    public void deleteUser(int id);
//    public List<UserDto> search(String name);
    public Page<User> getListUser(int page);
    public Page<UserDto> search(String name,String email,int page, int size);
}

