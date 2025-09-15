package com.munna.springboot.day4.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
	public String greet() {
		return "Hello from @Service Bean!";
	}

}
