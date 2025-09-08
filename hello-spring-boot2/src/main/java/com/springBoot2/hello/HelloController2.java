package com.springBoot2.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {
	
	@GetMapping("/")
	public String home() {
		return "Welcome to Spring Boot Day 2!";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello from custom port!";
	}

}
