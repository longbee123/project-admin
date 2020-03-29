package com.example.demo.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class HomeController  {

    @GetMapping("/signin")
    public String signIn() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "formsignin";
        }
       return "formsignin";
    }
    @GetMapping("/403")
    public String error403() {

        return "403";
    }

}
