package com.munna.springboot.day7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day7.exception.InvalidNameException;

@RestController
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping("/hello")
	public String sayHello(@RequestParam(defaultValue = "Munna") String name) {
		logger.info("Recived request to /hello with name: {}", name);
		
		// Custom exception example
	    if(name.equalsIgnoreCase("error")) {
	        throw new InvalidNameException("Name 'error' is not allowed!");
	    }
	    
		return "Hello, " + name + "!";
	}

}

/*

What I did:
------------
Created a REST endpoint /hello.
Added logging using SLF4J.
Default name is "Munna" if no query param is provided.


If the user passes ?name=error, it will throw a custom exception.
This will be caught by our GlobalExceptionHandler.

*/