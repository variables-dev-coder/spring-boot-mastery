package com.munna.springboot.day3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("/hello")
	public String sayHello() {
		logger.info("INFO: //hello endpoint called");
		logger.debug("DEBUG: Entering sayHello() method with extra details");
		return "Hello, Spring Boot ! (Day 3 - Properties and Logging)";
	}
}
