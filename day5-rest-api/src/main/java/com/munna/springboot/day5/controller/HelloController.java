package com.munna.springboot.day5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController    // tells Spring this class this class handles REST APIs
@RequestMapping("/api")  // base path for all endpoints
public class HelloController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from Day 5 REST API!";
	}

}
