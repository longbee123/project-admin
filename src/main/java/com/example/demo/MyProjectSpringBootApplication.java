package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

@SpringBootApplication
public class MyProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectSpringBootApplication.class, args);
	}

}
