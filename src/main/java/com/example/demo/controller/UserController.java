package com.example.demo.controller;



import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.RegisterForm;
import com.example.demo.model.request.UpdateForm;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;



@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getListUser(){
        List<UserDto> result = userService.getListUser();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getListUserbyId(@PathVariable int id){
        UserDto result = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PostMapping("/create")
    public ResponseEntity<?> creatUser(@Valid @RequestBody RegisterForm form, RedirectAttributes redirect){
        UserDto result = userService.createUser(form);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody  UpdateForm form , @PathVariable int id,RedirectAttributes redirect){
        UserDto result = userService.updateUser(form , id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id,RedirectAttributes redirect){
        userService.deleteUser(id);
        redirect.addFlashAttribute("successMessage", "Delete user successfully!");
        return ResponseEntity.status(HttpStatus.OK).body("Delete user successfully");
    }
//    @GetMapping("/search")
//    public ResponseEntity<?> search(@RequestParam("name") String name){
//        List<UserDto> result = userService.search(name);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
}
