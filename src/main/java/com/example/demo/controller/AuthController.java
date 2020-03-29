package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.dto.ResponseResult;
import com.example.demo.model.request.AuthenticateReq;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthenticateReq req, HttpServletResponse response) {
        // Xác thực từ username và password.


        ResponseResult result = new ResponseResult();
        try {
            // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Gen token
            String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

            result.setSuccess(true);
            result.setMessage("Đăng nhập thành công");

            Cookie jwtToken = new Cookie("jwt_token", token);
            jwtToken.setMaxAge(60 * 60 * 24);
            jwtToken.setPath("/");
            response.addCookie(jwtToken);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Email hoặc Password không đúng");
        }
        return ResponseEntity.ok(result);
    }
}
