package com.example.demo.controller;

import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class BrandTemplateController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/brand")
    public String list(Model model , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @ModelAttribute("name") String name)  {
//
        model.addAttribute("listuser" ,brandService.search( name , page , size));
        model.addAttribute("name",name);

        return "listbrand";
    }


    @GetMapping("/brand/add")
    public String add(){

        return "formbrand";
    }
    @GetMapping("/brand/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("brand", brandService.getBrandById(id));

        return "formbrandupdate";
    }
}
